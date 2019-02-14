/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebatecnica;

import java.math.BigInteger;
import java.util.ArrayList;
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

    public void registroBilletes(Billetes billetes, BigInteger cantidad) {
        billetes.setCantidad(billetes.getCantidad().add(cantidad));
        edit(billetes);
    }

    public ArrayList<Billetes> retirar(BigInteger valor) {
        ArrayList<Billetes> inventario = (ArrayList) findAll();
        ArrayList<Billetes> retiro = new ArrayList();
        BigInteger cantidad;
        BigInteger resto = valor;
        BigInteger faltante;

        for (Billetes b : inventario) {
            if (resto.compareTo(b.getDenominacion()) == 1) {
                Billetes billetes = new Billetes();
                cantidad = valor.divide(b.getDenominacion());
                resto = valor.mod(b.getDenominacion());
                billetes.setDenominacion(b.getDenominacion());
                faltante = disminuir(b.getDenominacion(), cantidad, b.getCantidad());
                billetes.setCantidad(cantidad.subtract(faltante));
                retiro.add(billetes);
                b.setCantidad(b.getCantidad().subtract(billetes.getCantidad()));
                resto.add(faltante.multiply(b.getDenominacion()));
            }
        }
        return retiro;
    }

    //retorna el valor que haria falta para completar la cantidad solicitada
    public BigInteger disminuir(BigInteger denominacion, BigInteger cantidad,BigInteger bdCantidad) {
        if (cantidad.compareTo(bdCantidad)==1){
            return cantidad.subtract(bdCantidad);
        }
        return new BigInteger("0");
    }
}
