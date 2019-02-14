/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebatecnica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author angie
 */
@Stateless
public class BilletesFacade extends AbstractFacade<Billetes> {

    @PersistenceContext(unitName = "com.mycompany_PruebaTecnica_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BilletesFacade() {
        super(Billetes.class);
    }

    /**
   * Este método aumenta el valor de cantidad de billetes. de acuerdo a la 
   * cantidad registrada, la suma al valor en existencia.
   * @param billetes es el objeto billetes al que se le agregaran mas cantidad
   * @param cantidad es el valor a adicionar al la cantidad existente
   * no retorna nada ya que es una operacion sencilla en la base de datos.
   */
    public void registroBilletes(Billetes billetes, int cantidad) {
        billetes.setCantidad(billetes.getCantidad() + cantidad);
        edit(billetes);
    }

    
    /**
   * Este método calcula la cantidad de billetes para retirar un valor. 
   * de acuerdo a un valor solicitado se calcula la cantidad de billetes por 
   * denominacion que cubren el valor solicitado.
   * @param valor es el valor a retirar en el cajero
   * @return arreglo de billetes con la cantidad por denominación que se deben 
   * entregar al usuario.
   */
    public ArrayList<Billetes> retirar(int valor) {
        List<Billetes> inventario = findAll();
        Collections.sort(inventario);
        ArrayList<Billetes> retiro = new ArrayList();
        int cantidad;
        int resto = valor;
        int faltante;

        for (Billetes b : inventario) {
            if (resto >= b.getDenominacion()) {
                Billetes billetes = new Billetes();
                cantidad = resto / b.getDenominacion();
                resto = resto % b.getDenominacion();
                billetes.setDenominacion(b.getDenominacion());
                faltante = disminuir(b.getDenominacion(), cantidad, b.getCantidad());
                billetes.setCantidad(cantidad - faltante);
                if (billetes.getCantidad() != 0) {
                    retiro.add(billetes);
                }
                resto += faltante * b.getDenominacion();
            }
        }

        if (resto != 0) {
            return new ArrayList();
        }else{
            editar(retiro, inventario);
        }
        return retiro;
    }

    /**
   * Este método modifica la cantidad de billetes que hay en el inventario. 
   * de acuerdo a la lista de biletes que se retiran, se actualizan sus valores 
   * en el inventario disminuyendolo segun corresponda.
   * @param retiro lista de billetes que se van a retirar
   * @param inventario lista de billetes en existencia en el cajero
   * @return String que notifica la acción realizada.
   */
    public String editar(ArrayList<Billetes> retiro, List<Billetes> inventario) {
        if (!retiro.isEmpty()) {
            for (Billetes bi : inventario) {
                for (Billetes br : retiro) {
                    if (bi.getDenominacion() == br.getDenominacion()) {
                        bi.setCantidad(bi.getCantidad() - br.getCantidad());
                        edit(bi);
                    }
                }
            }
            return "editar";
        }
        return "sin editar";
    }
    /**
   * Este método calculala la cantidad de billetes faltantes. 
   * se utiliza para validar la disponibilidad de billetes en el cajero para un 
   * valor solicitado
   * @param denominacion denominacion del billete que se quiere consultar
   * @param cantidad cantidad de billetes solicitados
   * @param bdCantidad cantidad de billetes en existencia
   * @return el valor de billetes faltantes para completar el valor solicitado.
   */
    public int disminuir(int denominacion, int cantidad, int bdCantidad) {
        if (cantidad > bdCantidad) {
            return cantidad - bdCantidad;
        }
        return 0;
    }
}
