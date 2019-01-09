package com.fiandeira.sftp;

import com.fiandeira.utils.Archivo;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author mreyesi
 */
public class SubirSFTP {
    
    private final static Logger log = Logger.getLogger(SubirSFTP.class);
    
    public static boolean subirArchivo(String username, String password, String host, int puerto, String pathOrigen, String pathDestino, Archivo archivo) {
        
        boolean error = false;
        
        log.info("Preparando envio de un archivo");
        
        try {
            
            log.info("Instanciando clase SFTPConnector");
            SFTPConnector sftp = new SFTPConnector();
            
            log.info("Conectando a servidor SFTP");
            sftp.connect(username, password, host, puerto);
            
            log.info("Enviando arhivo: " + archivo);
            sftp.addFile(pathDestino, pathOrigen, archivo.getNombreArchivo());
            
            log.warn("Desconectando de servidor SFTP");
            sftp.disconnect();
            
        } catch (JSchException | IllegalAccessException | IOException | SftpException e) {
            log.error("Error al enviar archivo : " + e);
            error = true;
        }
        
        return error;
        
    }
    
    public static boolean subirArchivos(String username, String password, String host, int puerto, String pathOrigen, String pathDestino, ArrayList<Archivo> listaArchivos) {
        
        boolean error = false;
        
        log.info("Preparando envio de varios archivos");
        
        try {
            
            log.info("Instanciando clase SFTPConnector");
            SFTPConnector sftp = new SFTPConnector();
            
            log.info("Conectando a servidor SFTP");
            sftp.connect(username, password, host, puerto);
            
            for(Archivo archivo : listaArchivos){
                log.info("Enviando arhivo: " + archivo);
                sftp.addFile(pathDestino, pathOrigen, archivo.getNombreArchivo());
            }
            
            log.warn("Desconectando de servidor SFTP");
            sftp.disconnect();
            
        } catch (JSchException | IllegalAccessException | IOException | SftpException e) {
            e.printStackTrace();
            log.error("Error al enviar archivo : " + e.getMessage());
            error = true;
        }
        
        return error;
        
    }
    
}
