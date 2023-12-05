package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadImpiccato extends Thread {
    Socket client;
    Parole p;
    BufferedReader inputClient;
    DataOutputStream outputClient;
    ArrayList<Socket> listaClient;

    public ThreadImpiccato(Socket client,Parole p, ArrayList<Socket> listaClient) {
        //per non stare a usare this
        this.client = client;
        this.p = p;
        this.listaClient = listaClient;
        listaClient.add(client);
    }

    public void run() {
        Parole p = this.p;
        Socket client = this.client;
        try {
            // creao i tubi
            this.inputClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.outputClient = new DataOutputStream(client.getOutputStream());
            BufferedReader leggiTastiera = new BufferedReader(new InputStreamReader(System.in));
            String stringaFinale = "";
            String invioClient;
            int scelta=0;
            String parola= p.casuale();
            int tentativi=0;
            int posizioneLettera=-1;
            String stringaAsterischi="";
            ArrayList<Character> lettereScoperte= new ArrayList<>();
            // invio al client
            do {
                invioClient = inputClient.readLine();
                invioClient = invioClient.toUpperCase();
                System.out.println("il client " + client.getInetAddress() + " ha digitato:" + invioClient + " Digita ESCI per terminare");

                if(invioClient.length()==1){
                    scelta=1;
                }
                 if(invioClient.length()>1){
                    scelta=2;
                }
                if(invioClient=="ESCI"){
                    scelta=3;
                }
               

                // non riesco aq risolvere il problema di logica perche mi sono ingarbugliato da solo e non so come continuare, se mette il suo metodo di come 
                //fare a pasare al client le parole con gli asterischi e lettere giuste secondo me dovrebbe funzionatere
                switch (scelta) {
                    case 1:
                        if (parola.contains(invioClient)) {
                            posizioneLettera= parola.indexOf(invioClient);
                            for(int i=0; i<=parola.length(); i++){
                                if(i==posizioneLettera){
                                    stringaAsterischi+=(invioClient);
                                    lettereScoperte.add(Character)(invioClient);
                                }
                                else{
                                    for(int y=0; y<=lettereScoperte.size(); y++){
                                        if(parola.charAt(i)==lettereScoperte.get(y));
                                    }
                                }
                            }
                        }

                        tentativi++;
                        break;
                    case 2:
                        if(parola.equals(invioClient)){
                            stringaFinale="Bravo, hai indovinato con " + tentativi + " tentativi";
                        }

                        else{
                            
                        }
                        
                        break;

                    case 3:
                        stringaFinale = "ESCI";
                        System.out.println(" il client " + client.getInetAddress() + " ha richiesto di chiudere");
                        break;

                    default:
                        stringaFinale = "inserimento errato";
                        break;
                }

                outputClient.writeBytes(stringaFinale + "\n");
            } while (!invioClient.equals("ESCI"));
            System.out.println("\n chiusura con il client " + client.getInetAddress());
        } catch (Exception e) {
            System.out.println("errone nella comunicazione: " + e.getMessage());
        }
    }
}
