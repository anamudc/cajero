/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebatecnica;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 *
 * @author angie
 */
public class BilletesFacadeTest {
    
    public BilletesFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Test of registroBilletes method, of class BilletesFacade.
     */
//    @org.junit.Test
    public void testRegistroBilletes() throws Exception {
        System.out.println("registroBilletes");
        Billetes billetes = null;
        BigInteger cantidad = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        BilletesFacade instance = (BilletesFacade)container.getContext().lookup("java:global/classes/BilletesFacade");
        instance.registroBilletes(billetes, cantidad);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retirar method, of class BilletesFacade.
     */
    @PersistenceContext(unitName = "com.mycompany_PruebaTecnica_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @org.junit.Test
    public void testRetirar() throws Exception {
        
        
        System.out.println("retirar");
        BigInteger valor = new BigInteger("280000");
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        BilletesFacade instance = new BilletesFacade();//container.getContext().lookup("java:global/classes/BilletesFacade");
        BilletesFacade instanceSpy = spy(instance);
        List<Billetes> listOfBilletes = new ArrayList<>();
        Billetes b1 = new Billetes();
        Billetes b2 = new Billetes();
        Billetes b3 = new Billetes();
        b1.setDenominacion(new BigInteger("50000"));
        b1.setCantidad(new BigInteger("0"));
        b2.setDenominacion(new BigInteger("20000"));
        b2.setCantidad(new BigInteger("0"));
        b3.setDenominacion(new BigInteger("10000"));
        b3.setCantidad(new BigInteger("7"));
        listOfBilletes.add(b1);
        listOfBilletes.add(b2);
        listOfBilletes.add(b3);
        
        doReturn(listOfBilletes).when(instanceSpy).findAll();
        
        
        
        
        ArrayList<Billetes> expResult = new ArrayList<>();
        
        ArrayList<Billetes> result = instanceSpy.retirar(valor);
        
        assertEquals(expResult, result);
//        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disminuir method, of class BilletesFacade.
     */
//    @org.junit.Test
    public void testDisminuir() throws Exception {
        System.out.println("disminuir");
        BigInteger denominacion = null;
        BigInteger cantidad = null;
        BigInteger bdCantidad = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        BilletesFacade instance = (BilletesFacade)container.getContext().lookup("java:global/classes/BilletesFacade");
        BigInteger expResult = null;
        BigInteger result = instance.disminuir(denominacion, cantidad, bdCantidad);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
