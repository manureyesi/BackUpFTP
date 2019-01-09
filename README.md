# BackUpFTP
BackUp en Java para subir a SFTP.

## Configuracion

#### Librerias:

- log4j para crear logs
- jsch para conexion SFTP

Este programa necesita un archivo de configuracion del host a conectar, que tiene que tener la siguiente estructura:

<datos>
    <path>C:\Users\mreyesi\Documents\NetBeansProjects\BackUpFTP\hola\</path>
    <ip>www.fiandeira.es</ip>
    <port>22</port>
    <user>fiandeira</user>
    <pass>fiosebotons</pass>
	<pathDestino>home</pathDestino>
</datos>
