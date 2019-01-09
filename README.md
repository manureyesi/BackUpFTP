# BackUpFTP
BackUp en Java para subir a SFTP.

## Configuracion

#### Librerias:

- log4j para crear logs
- jsch para conexion SFTP

Este programa necesita un archivo de configuracion del host a conectar, que tiene que tener la siguiente estructura:

```
<datos>
    <path>C:\EJEMPLO\</path>
    <ip>XXXXXXXXXXX</ip>
    <port>22</port>
    <user>XXXXXXXXXXX</user>
    <pass>XXXXXXXXXXX</pass>
    <pathDestino>XXXXXXXXXXX</pathDestino>
</datos>
```
