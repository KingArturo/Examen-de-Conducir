# Autoescuela

Aplicacion que apartir de la información de una base de datos permite realizar examenes de autoescuela

## Interface 🚀

La interface dispone de una pantalla que contiene dos botones y que permite selecciones la base de datos (sqlite) que contiene los 
examenes.<br />
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/pantalla1.png)<br />
Cuando se pulsa el boton _Empezar_ se cierra la ventana que permite seleccionar los examenes y se muestra la ventana en 
la que se mostraran los examenes y donde se podrán hacer los examenes.<br />
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/pantalla2.png)<br />
En esa misma ventana podremmos cambiar el numero de preguntas de los examenes.<br />
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/numPreguntas.png)<br />
Tambien cuenta con un menu que permite visualizar una grafica con los examenes realizados.<br />
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/grafica.png)<br />
Ese mismo menu contiene una opción que permite añadir una pregunta.<br />
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/addPregunta.png)<br />
Y por último el menu cuanta con una opcion que permite regresar al la ventana de seleccion de examenes.<br />
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/salir.png)<br />

## Pre-requisitos 📋

Para poder ejecutar la aplicación correctamente se necesitara windows

## Instalación 🔧

En el repositorio va incluido un instalador que permite instalar la plicación.

_Para instalar la aplicación solamente tendrás que ejecutar el instalador y seguir los pasos._

Una vez instalado la aplicación deberá ejecutarse como administrador, de otra forma si intentas añadir una pregunta no 
te dejara añadir la imagen.

## Código ⚙️

La clase dispone de las siguientes clases:
* ElegirPreguntasFrame
* TestFrame
* PreguntasPanel
* PreguntaButton
* ExamenButton
* SelectCantPrePanel
* ConexionDB
* AddPreguntaFrame
* ResultadosPanel

### Clase ElegirPreguntasFrame

Clase principal que extiende de JFrame y que crea una ventana con dos botones:
* Uno que al pulsarlo mustra un explorador de archivos que permite seleccionar una base de datos sqlite.
* Otro que permite una vez seleccionada la base de datos crear la ventana donde se podrán hacer los exámenes.

### TestFrame
Clase que contiene los examenes que se pueden realizar. Tambien permite seleccionar la cantidad de 
preguntas que tendrán los examenes, asi como un botón que permite cambiar el tema de la aplicación.
Tambien contiene un menu llamado _Tools_ que contiene 3 MenuItems:
* El primero permite visualizar la cantidad de examenes realizados de cada tipo y los que has aprobado o suspendido.
* La segunda opcion crea una ventana nueva que permite añadir una pregunta a un examen.
* La tercera cierra la venta y crea una ventana de seleccion de la base de datos.

### PreguntasPanel
Clase que crea un Panel en el que se irán mostrnado las preguntas(de un determinado examen) de manera aleatoria junto con sus respuestas, 
tambien dispone de un cronometro de 5 minutos que cuando llega a 0 finaliza el examen. 
El examen se acaba cuando el cronometro llega a 0 o se contestan todas las preguntas. 
Al finalizar el examen saldrá un dialogo informando sobre el resultado del examen.

### PreguntaButton
Clase que extiende de JButton y que contendra el texto de una respuesta a si como si esta es correcta o no. 

### ExamenButton
Clase que extiende de JButton y contiene el nombre de un examen y su id.

### SelectCantPrePanel
Clase que extiende de JPanel y  que contiene un Slider que servira para seleccionar un numero del 5 al 10

### ConexionDB
Clase que se encarga de establecer una conexión con una base de datos y realizar las consultas que se necesite.

### AddPreguntaFrame
Clase que extiende de JFrame y que contiene un formulario con un ComboBox que contiene los examenes existentes asi como 
un campo TextArea y 4 TextFiel en los que se introducira la nueva pregunta que se desee añadir.
Tambien dispone de un boton que abre un explorador de archivo en el que se podrá seleccionar la imagen que 
hace referencia a la pregunta, una vez seleccionado la imagen se copiara a la carpeta resources del proyecto.

### ResultadosPanel
Calse que exitende de JPanel y que muestra las preguntas de un examen junto con la respuesta seleccionada y 
si es o no correcta. Para elegir que pregunta se quiere ver el Frame cuanta con un slider con el que podremos 
seleccionar la pregunta que queramos ver.

## Base de datos
La base de datos dispondrá de 4 tablas:
* examenes
* preguntas
* respuestas
* registro

### examenes
La tabla examenes cuenta con dos campos:
* id que es un INTEGER(9) y es calve primaria.
* nombre que es un VARCHAR(20).

### preguntas
La tabla preguntas cuenta con 4 campos:
* id que es un INTEGER(9) y es calve primaria.
* pregunta que es un VARCHAR(100).
* imagen que es un VARCHAR(100).
* examen que es un INTEGER(9) y es una clavle ajena que hace referencia con el campo id de la tabla examenes.

### respuestas
La tabla respuestas cuenta con 4:
* id que es un INTEGER(9) y clave primaria.
* respuesta que es un VARCHAR(100).
* correcta que es un VARCHAR(5).
* pregunta que es un INTEGER(9) y es una clavle ajena que hace referencia con el campo id de la tabla preguntas.

### registro
Tabla que contiene 2 campos:
* aciertos que es un INT(9).
* examen que es un INTEGER(9) y es una clavle ajena que hace referencia con el campo id de la tabla examenes.

## Autor ✒️

* **Arturo Monje Robles** - *Trabajo Inicial* - [KingArturo](https://github.com/KingArturo)