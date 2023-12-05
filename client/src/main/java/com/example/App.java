package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            // creao un socket / collegamento al server
            Socket socket = new Socket("localhost", 3000);
            // creo i buffer e stream per comunicare
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader inputServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader leggiTastiera = new BufferedReader(new InputStreamReader(System.in));
            int scelta=0;
            String stringaDaMandare = "";
            Ascolto a = new Ascolto(inputServer);
            a.start();
            do {
                String menu="1) il client sceglie una lettera 2) Il client prova ad indovinare la parola 3) Il client si disconnette";
                System.out.println(menu);
                switch (scelta) {
                    case 1:
                        System.out.println("scegli una lettera");
                        stringaDaMandare = leggiTastiera.readLine();
                        stringaDaMandare = stringaDaMandare.toUpperCase();  
                        break;
                    
                    case 2:
                        System.out.println("Prova a indovinare la parola");
                        stringaDaMandare = leggiTastiera.readLine();
                        stringaDaMandare = stringaDaMandare.toUpperCase();  
                        break;
                    
                    case 3:
                        System.out.println("Hai scelto di uscire");
                        stringaDaMandare="ESCI";

                    default:
                        stringaDaMandare = "inserimento errato";
                        break;
                }
                
                dataOutputStream.writeBytes(stringaDaMandare+"\n");   
            } while (!stringaDaMandare.equals("ESCI"));
            socket.close();

        } catch (Exception e) {
            System.out.println("Errore");
        }
    }
}
