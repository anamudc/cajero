/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;


/**
 *
 * @author angie
 */
public class Cajero {
    
    public String registroBilletes(int denominacion, int cantidad){
//        Billetes billetes = new Billetes();
//        
//        billetes.setDenominacion(denominacion);
//        billetes.setCantidad(cantidad);
        
        
        
        return "";
    }
    
    public void retirar(int valor){
        
        
    }
    
    
    //retorna el valor que haria falta para completar la cantidad solicitada
    public int disminuir(int denominacion, int cantidad){
        int bdCantidad = 5;//bdCantidad consultar cuantos billetes hay en la base de datos
        if(cantidad>bdCantidad){
            //cambiar la cantidad en la base de datos a cero
            return cantidad-bdCantidad;
        }else{
            //disminuir la cantidad en la base de datos (bdCantidad - cantidad)
        }
        return 0;
    }
    
}
