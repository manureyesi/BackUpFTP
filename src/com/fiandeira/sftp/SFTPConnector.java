package com.fiandeira.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
 
/**
 * Clase encargada de establecer conexion y ejecutar comandos SFTP.
 */
public class SFTPConnector {
 
    /**
     * Sesion SFTP establecida.
     */
    private Session session;
 
    private final static Logger log = Logger.getLogger(SFTPConnector.class);
    
    /**
     * Establece una conexion SFTP.
     *
     * @param username Nombre de usuario.
     * @param password Contrasena.
     * @param host     Host a conectar.
     * @param port     Puerto del Host.
     *
     * @throws JSchException          Cualquier error al establecer
     *                                conexión SFTP.
     * @throws IllegalAccessException Indica que ya existe una conexion
     *                                SFTP establecida.
     */
    public void connect(String username, String password, String host, int port)
        throws JSchException, IllegalAccessException {
        if (this.session == null || !this.session.isConnected()) {
            JSch jsch = new JSch();
            
            log.info("Conectando a servidor SFTP ["+host+":"+port+"] con usuario "+username+" .");
            
            this.session = jsch.getSession(username, host, port);
            this.session.setPassword(password);
            
            // Parametro para no validar key de conexion.
            //extra config code
            java.util.Properties config = new java.util.Properties();
            
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            log.info("Configuracion de StrictHostKeyChecking enviada.");
            
            this.session.connect();
            log.info("Conexion establecida.");
        } else {
            log.warn("Sesion SFTP ya iniciada.");
            throw new IllegalAccessException("Sesion SFTP ya iniciada.");
        }
    }
 
    /**
     * Añade un archivo al directorio FTP usando el protocolo SFTP.
     *
     * @param ftpPath  Path del FTP donde se agregará el archivo.
     * @param filePath Directorio donde se encuentra el archivo a subir en
     *                 disco.
     * @param fileName Nombre que tendra el archivo en el destino.
     *
     * @throws IllegalAccessException Excepción lanzada cuando no hay
     *                                conexión establecida.
     * @throws JSchException          Excepción lanzada por algún
     *                                error en la ejecución del comando
     *                                SFTP.
     * @throws SftpException          Error al utilizar comandos SFTP.
     * @throws IOException            Excepción al leer el texto arrojado
     *                                luego de la ejecución del comando
     *                                SFTP.
     */
    public final void addFile(String ftpPath, String filePath,
        String fileName) throws IllegalAccessException, IOException,
        SftpException, JSchException {
        if (this.session != null && this.session.isConnected()) {
            
            log.info("Preparando envio de archivo "+fileName+" a carpeta de servidor "+ftpPath+".");
            // Abrimos un canal SFTP. Es como abrir una consola.
            ChannelSftp channelSftp = (ChannelSftp) this.session.
                openChannel("sftp");
 
            channelSftp.connect();
            
            // Nos ubicamos en el directorio del FTP.
            log.info("Entrando en directorio "+ftpPath+" de servidor.");
            channelSftp.cd(ftpPath);
            
            log.info(String.format("Creando archivo %s en el " +
                "directorio %s", fileName, ftpPath));
            
            Date date = new Date();
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(date);
            
            channelSftp.put(filePath + fileName, fecha+fileName);
            
            log.info("Archivo subido exitosamente");
 
            channelSftp.exit();
            channelSftp.disconnect();
            
        } else {
            log.warn("No existe sesion SFTP iniciada.");
            throw new IllegalAccessException("No existe sesion SFTP iniciada.");
        }
    }
 
    /**
     * Cierra la sesion SFTP.
     */
    public final void disconnect() {
        this.session.disconnect();
        log.warn("Cerrando conexion SFTP");
    }
}
