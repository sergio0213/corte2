/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unbosque;

import com.unbosque.singletonsf2.BaseDeDatos;
import com.unbosque.singletonsf2.Hilos;
import java.sql.SQLException;

import org.testng.annotations.Test;

/**
 *
 * @author scabrera
 */
public class pruebas {

    public pruebas() {
    }

    @Test
    public static void probarConexionBD() throws ClassNotFoundException {

        BaseDeDatos bd = BaseDeDatos.getInstancia();
        bd.getConnection();
      
    }
      @Test
    public static void probarIniciarHilos() throws ClassNotFoundException {

        Hilos a = new Hilos();
        a.iniciarHilos();
        
    }
    
       @Test
    public static void probarSelectHilos() throws SQLException, ClassNotFoundException {

        Hilos a = new Hilos();
        a.selectHilos();
         BaseDeDatos.getInstancia().Close();
    }
    
}
