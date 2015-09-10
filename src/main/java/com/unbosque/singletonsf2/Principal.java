/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unbosque.singletonsf2;

/**
 *
 * @author scabrera
 */
public class Principal {

    public Principal() {
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Hilo2 a = new Hilo2();
        a.iniciarHilo2();
    }
}
