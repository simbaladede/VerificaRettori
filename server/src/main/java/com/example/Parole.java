package com.example;

import java.util.ArrayList;

public class Parole {
    ArrayList<String> arrayParole;

    public Parole(){
        arrayParole= new ArrayList<String>();
    }


    public void riempi(){
        arrayParole.add("2 ciao");
        arrayParole.add("2 montagna");
        arrayParole.add("2 campeggio");
        arrayParole.add("2 serpe");
        arrayParole.add("2 squalo");  
        arrayParole.add("2 pecora"); 
    }


    public String casuale(){
         int random = (int)(Math.random() * (arrayParole.size()) + 1);
         return arrayParole.get(random);
    }
}
