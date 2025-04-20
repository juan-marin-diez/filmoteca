# filmoteca
Proyecto android de DAM
## UD02
### Ejercicio 1
![Aplicaciones y servicios instalados](installed-apps.png)
### Ejercicio 2
![Versión de Android](installed-android-version.png)
### Ejercicio 22
Se ejecuta Android Monkey desde el dispositivo (a través de Android Debug Brige) mediante el siguiente comando
```bash
monkey -p com.campusdigitalfp.filmoteca -s 2025 --throttle 500 500
```
![Ejecución de Android Monkey](monkey-run.png)
![monkey-result.png](monkey-result.png)
## UD03
### Ejercicio 2
Creación del proyecto en Firebase. Introducimos el nombre del proyecto, y aceptamos las condiciones
![firebase1.jpg](firebase1.jpg)
Habilitamos Google Analytics para obtener estadísiticas del uso del proyecto
![firebase2.jpg](firebase2.jpg)
Especifiamos la ubicación de Google Analytics y aceptamos la configuración predeterminada
![firebase3.jpg](firebase3.jpg)
Tenemos el proyecto listo para iniciar el proyecto
![firebase4.jpg](firebase4.jpg)
Iniciamos la creación de una aplicación Android, para tener acceso a Firestore, Authentication y Storage
![firebase5.jpg](firebase5.jpg)
Completamos los datos requeridos, el nombre del paquete que usaremos en Android Studio, al que le podemos poner un sobrenombre. También le podemos indicar la firma SHA-1 de un certificado, aunque se puede hacer posteriormente.
![firebase6.jpg](firebase6.jpg)
Descargamos el archivo google-services.json y lo pegamos en el directorio app de nuestro proyecto de Android Studio
![firebase7.jpg](firebase7.jpg)
Modificamos los ficheros build.gradle.kts, tanto el de Proyecto como el de Aplicación con la configuración facilitada
![firebase8.jpg](firebase8.jpg)
Ya hemos terminado de configurar Firebase para nuestra aplicación Android
![firebase9.jpg](firebase9.jpg)