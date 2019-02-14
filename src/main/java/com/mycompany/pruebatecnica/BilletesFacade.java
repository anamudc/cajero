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

    public void registroBilletes(Billetes billetes, int cantidad) {
        billetes.setCantidad(billetes.getCantidad() + cantidad);
        edit(billetes);
    }

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
        return "!editar";
    }

    //retorna el valor que haria falta para completar la cantidad solicitada
    public int disminuir(int denominacion, int cantidad, int bdCantidad) {
        if (cantidad > bdCantidad) {
            return cantidad - bdCantidad;
        }
        return 0;
    }
}
