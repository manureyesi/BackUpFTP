package com.fiandeira.utils;

public class Archivo {
    
    private final String path;
    private String nombreArchivo;
    
    public Archivo(String path){
        this.path = path.replace('\\', '/');
        
        devolverNombreArchivo();
    }

    private void devolverNombreArchivo(){
        
        String[] nombres = this.path.split("/");
        
        this.nombreArchivo = nombres[nombres.length-1];
        
    }
    
    public String getPath() {
        return path;
    }
    
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    
}
