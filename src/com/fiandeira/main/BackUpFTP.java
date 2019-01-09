package com.fiandeira.main;

import com.fiandeira.utils.Directorio;
import com.fiandeira.configuracion.XMLCargarConfiguracion;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.SAXException;
import com.fiandeira.sftp.SubirSFTP;
import com.fiandeira.utils.Archivo;
import com.fiandeira.utils.Lista;

public class BackUpFTP {

    private final static Logger log = Logger.getLogger(BackUpFTP.class);
    
    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        
        log.info("Preparar inicio de BackUp");
        log.info("Preparar lectura de archivo conf");
        
        boolean error = false;
        XMLCargarConfiguracion arc = null;
        
        try{
            log.info("Cargando configuracion");
            arc = new XMLCargarConfiguracion();
        }
        catch(IOException | ParserConfigurationException | SAXException e){
            log.error("Error al cargar datos de archivo: ", e);
            error = true;
        } catch(Exception e){
            log.error("Error al cargar datos de archivo: ", e);
            error = true;
        }
        
        if(!error){
            
            log.info("Creando Array vacio");
            ArrayList<Archivo> lista = new ArrayList();
            
            try {
                String path = arc.getPath() != null ? arc.getPath() : "";
                lista = Lista.formatearLista(new Directorio().listaDirectorios(path));
                
                //Subir a SFTP
                log.info("Preparando para subir archivos");
                error = SubirSFTP.subirArchivos(arc.getUser(), arc.getPass(), arc.getIp(), arc.getPort(), arc.getPath(), arc.getPathDestino(), lista);
                
                if(error){
                    log.error("Error al subir archivos");
                }
                else{
                    log.info("Archivos subidos correctamente");
                }
                
            } catch (IOException e) {
                log.error("Error al crear lista", e);
            }
                        
        }
        
    }
    
}
