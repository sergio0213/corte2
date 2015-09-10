/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unbosque.singletonsf2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scabrera
 */
public class Hilo2 extends Thread {

    private String nombre;
    private int n = 0;
    private String sql;
    BaseDeDatos bd;

    public Hilo2() {

    }

    // Constructor
    public Hilo2(String s, int n) throws ClassNotFoundException {
        this.nombre = s;
        sql = "INSERT INTO hilos VALUES(?,?);";
        bd = BaseDeDatos.getInstancia();
    }

    // threads. Cuando run() termina el thread muere
    @Override
    public void run() {
        // guardamos en la base de datos
        try {
            long time_start, time_end;
            time_start = System.currentTimeMillis();
            PreparedStatement ps = bd.getConnection().prepareStatement(sql);
           ps.setString(1, nombre);
            for (int i = 0; i < 5000; i++) {
                n++;
                ps.setInt(2, n);
                ps.executeUpdate();
                System.out.println(" int:" + n);

            }
          
            time_end = System.currentTimeMillis();
            System.out.println("#\n " + nombre + " Duracion: " + (time_end - time_start) + " milliseconds");
        } catch (SQLException ex) {
            System.out.println("ERROR" + ex.getMessage());
            bd.Close();

        }

    }

    public void iniciarHilo2() throws ClassNotFoundException {
        Hilo2 t1, t2, t3;

        // Creamos los threads
        t1 = new Hilo2("Thread 1", (int) n);
        t2 = new Hilo2("Thread 2", (int) n);
        t3 = new Hilo2("Thread 3", (int) n);
        // Arrancamos los threads
        t1.start();
        t2.start();
        t3.start();

    }

    public void selectHilo2() throws SQLException, ClassNotFoundException {
        bd = BaseDeDatos.getInstancia();
        Statement s = bd.getConnection().createStatement();
        ResultSet rs = s.executeQuery("Select nombre, numero from grupo2.hilos order by nombre");

        while (rs.next()) {
            System.out.println("NOMBRE:" + rs.getString("nombre") + "N:" + rs.getString("numero"));
        }

    }

}
