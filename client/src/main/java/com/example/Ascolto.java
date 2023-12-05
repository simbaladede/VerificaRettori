package com.example;

import java.io.BufferedReader;

public class Ascolto extends Thread {
    BufferedReader inputServer;
    public Ascolto(BufferedReader input){
        this.inputServer = input;
    }
    public void run(){
        String stringaRisposta = "";
        BufferedReader inputServer = this.inputServer;
        try {
            do{
                stringaRisposta = inputServer.readLine();
                System.out.println("\t il server ha risposto: " + stringaRisposta);
                stringaRisposta = stringaRisposta.toUpperCase();
            }while(!stringaRisposta.equals("ESCI"));
        } catch (Exception e) {
            System.out.println("errore");
        }
    }
}
