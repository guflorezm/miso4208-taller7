# miso4208-taller7
Repositorio Taller 7 MISO-4208  

## Monkey ad-hoc utilizando Adb shell input

Para ejecutar myAndroidMonkey necesita tener instalado Java y Android Studio. Puede descargar el jar que se encuentra en el siguiente enlace https://github.com/guflorezm/miso4208-taller7/blob/master/jar/myAndroidMonkey.jar y ejecutarlo de la siguiente forma por linea de comando:

java -jar myAndroidMonkey.jar adb_root=C:/Users/Usuario/AppData/Local/Android/sdk/platform-tools/ apk=D:/Gerson/Uniandes/me.kuehle.carreport_61.apk package=me.kuehle.carreport telnet_token=86FBGBT6r/YOAN9z emulator_port=5554 number_events=20 events=text,keyevent,tap,swipe distribution=0.2,0.1,0.4,0.3

Los argumentos que se envian son:

* adb_root : Contiene la ruta del sdk de android donde se encuentra el ADB ej: adb_root=C:/Users/Usuario/AppData/Local/Android/sdk/platform-tools/ en este caso es una ruta windows, debe terminar con backslash (/) al final
* apk : Contiene la ruta y el nombre del apk a probar ej: apk=D:/Gerson/Uniandes/me.kuehle.carreport_61.apk
* package : Nombre del paquete de la app a probar ej: package=me.kuehle.carreport
* telnet_token : Token del telnet ej: telnet_token=86FBGBT6r/YOAN9z
* emulator_port : Puerto del emulador ej: emulator_port=5554
* number_events : Numero de eventos a enviar ej: number_events=20
* events : Los eventos a enviar, deben ir separados por coma ej: events=text,keyevent,tap,swipe
* distribution : La distribucion de probabilidades de los eventos, deben ir separados por coma y se corresponden con el numero de eventos y posicion de estos en events ej: distribution=0.2,0.1,0.4,0.3 el total de la sumatoria de estos debe ser igual a 1

Tambien puede descargar el proyecto en java desde este repositorio e importarlo en eclipse y ejecutarlo desde el IDE 

A continuación puede ver la ejecución de la prueba en el siguiente video

![myAndroidMonkey](https://github.com/guflorezm/miso4208-taller7/blob/master/imagenes/myAndroidMonkey.gif)

## Espresso Test Recorder

La prueba de espresso test recorder se realizó con la aplicación CAR REPORT cuyo codigo fuente se encuentra en el siguiente enlace https://bitbucket.org/frigus02/car-report 

A continuación se puede ver la creación del test record and replay con espresso en el siguiente video

![espressoTestCrear](https://github.com/guflorezm/miso4208-taller7/blob/master/imagenes/espresso-test-recorder-crear.gif)

A continuación se puede ver la ejecución de la prueba en el siguiente video

![espressoTestEjecutar](https://github.com/guflorezm/miso4208-taller7/blob/master/imagenes/espresso-test-recorder-ejecutar.gif)

## Espresso Punto 4

La prueba de espresso se realizó con la aplicación CAR REPORT cuyo codigo fuente se encuentra en el siguiente enlace https://bitbucket.org/frigus02/car-report 

A continuación se puede ver la ejecución del test con espresso en el siguiente video

![espresso](https://github.com/guflorezm/miso4208-taller7/blob/master/imagenes/espresso-4.gif)




