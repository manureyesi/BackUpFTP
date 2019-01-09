package com.fiandeira.utils;

import java.io.File;

public class ComprobarFichero {
    
    public static boolean comprobarSiExiste(String path){
        
        File af = new File(path);
        
        return af.exists();
    }
    
    public static boolean comprobarSiEsUnFichero(String path){
        
        File af = new File(path);
        
        return af.isFile();
        
    }
    
}
