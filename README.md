# filmoteca
Proyecto android de DAM
## UD02 Android básico
### Ejercicio 1
![Aplicaciones y servicios instalados](installed-apps.png)
### Ejercicio 2
![Versión de Android](installed-android-version.png)
### Ejercicio 22
Se ejecuta Android Monkey desde el dispositivo (a través de Android Debug Brige) mediante el siguiente comando
```bash
monkey -p app.is_mobile.filmoteca -s 2025 --throttle 500 500
```
![Ejecución de Android Monkey](monkey-run.png)
![monkey-result.png](monkey-result.png)

## UD03 Android Avanzado
### Ejercicio 1
![Se establece el nombre del proyecto](firebase1.jpg)
![Habilitamos Google Analytics](firebase2.jpg)
![Configuramos Google Analytics](firebase3.jpg)
![Tras configurarse Google Analytics hacemos clic en continuar](firebase4.jpg)
![Añadimos nuestra app, en nuestro caso seleccionamos Android](firebase5.jpg)
![Agregamos el nombre del paquete](firebase6.jpg)
![Nos descargamos el fichero google-service.json](firebase7.jpg)
![Guardamos el fichero en la carpeta app de nuestro proyecto](firebase8.jpg)
![Configuramos las dependencias y plugins en Gradle](firebase9.jpg)
![Comprobamos que hemos terminado todos los pasos](firebase10.jpg)