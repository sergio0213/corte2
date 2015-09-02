/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unbosque.singletonsf2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scabrera
 */
public class Hilos extends Thread {

    private String nombre;
    private int n = 0;
    private String sql;
    BaseDeDatos bd;

    public Hilos() {

    }

    // Constructor
    public Hilos(String s, int n) throws ClassNotFoundException {
        this.nombre = s;
        sql = "INSERT INTO hilos VALUES('" + nombre + "'," + n + ");";
        bd = BaseDeDatos.getInstancia();
    }

    // threads. Cuando run() termina el thread muere
    @Override
    public void run(){
        // guardamos en la base de datos
        
        try {
            Statement s = bd.getConnection().createStatement();
            s.execute(sql);
            System.out.println(nombre + "-" + n + " sql:" + sql);
            s.close();
        } catch (SQLException ex) {
             System.out.println("ERROR"+ex.getMessage());
            bd.Close();
          
           
        }

    }

    public void iniciarHilos() throws ClassNotFoundException {
        Hilos t1, t2, t3;
        for (int i = 0; i < 2; i++) {
            n++;
            // Creamos los threads
            t1 = new Hilos("Thread 1", (int) n);
            t2 = new Hilos("Thread 2", (int) n);
            t3 = new Hilos("Thread 3", (int) n);

            // Arrancamos los threads
            t1.start();
            t2.start();
            t3.start();

        }

    }

    public void selectHilos() throws SQLException, ClassNotFoundException {
        bd = BaseDeDatos.getInstancia();
        Statement s = bd.getConnection().createStatement();
        ResultSet rs = s.executeQuery("Select nombre, numero from grupo2.hilos order by nombre");
       
        while (rs.next()) {
            System.out.println("NOMBRE:" + rs.getString("nombre")+"N:" + rs.getString("numero"));
        }

    }

}
