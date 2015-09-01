/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unbosque.singletonsf2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author ingeneo
 */
public class BaseDeDatos {

    /**
     * Driver de la base de datos
     */
    private static java.sql.Driver driver = null;
    /**
     * Conexion a la base de datos
     */
    private static java.sql.Connection connection = null;
    /**
     * Instancia de la clase cDataBase
     */
    private static BaseDeDatos instancia = null;

    /**
     * Constructor privado. Llama al metodo connect()
     */
    private BaseDeDatos() throws ClassNotFoundException {
        this.connect();
    }

    /**
     * Metodo static q llama al constructor privado
     *
     * @returns Instancia unica de la clase
     */
    public static BaseDeDatos getInstancia() throws ClassNotFoundException {
        if (instancia == null) {
            instancia = new BaseDeDatos();
        }
        return instancia;
    }

    /**
     * obtener la conexion
     * @returns Conexion a la base de datos
     */
    public java.sql.Connection getConnection() {
        return this.connection;
    }

    /**
     * Metodo que se conecta a la base de datos de la aplicacion
     */
    private void connect() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver"); // Carga del driver en memoria.
        String databaseURL = "jdbc:postgresql://aretico.com:5432/software_2";
        String user = "grupo2";
        String password = "9AE7xst0iD";

        try {

// Ahora se intentara conseguir una conexion con la base de datos
            try {
                connection = java.sql.DriverManager.getConnection(databaseURL, user, password);
                System.out.println("Conexion establecida.");
            } 
         
            catch (java.sql.SQLException e) {
                System.err.println("Error al establecer la conexion."+e.getMessage());
                return;
            }

// Deshabilitacion del autocommit
            try {
                connection.setAutoCommit(false);
                System.out.println("Auto-commit deshabilitado.");
            } catch (java.sql.SQLException e) {
                System.out.println("Error al deshabilitar el auto-commit.");
                return;
            }
        } finally {
            return;
        }
    }

    /**
     * Cierra la conexion a la base de datos
     */
    public void Close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (java.sql.SQLException e) {
            System.out.println("No se pudo cerrar la conexion a la base de datos");
        }
    }
}
