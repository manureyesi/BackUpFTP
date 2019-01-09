package com.fiandeira.utils;

import java.util.ArrayList;

public class Lista {
    
    public static ArrayList<Archivo> formatearLista(ArrayList<String> lista){
    
        ArrayList<Archivo> archivos = new ArrayList<>();
     
        for(String i: lista){
            archivos.add(new Archivo(i));
        }
        
        return archivos;
    }
    
}
