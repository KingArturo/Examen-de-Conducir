# Autoescuela

Aplicacion que apartir de la información de una base de datos permite realizar examenes de autoescuela

## Interface 🚀

La interface dispone de una pantalla que contiene dos botones y que permite selecciones la base de datos (sqlite) que contiene los 
examenes.
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/pantalla1.png)
Cuando se pulsa el boton _Empezar_ se cierra la ventana que permite seleccionar los examenes y se muestra la ventana en 
la que se mostraran los examenes y donde se podrán hacer los examenes.
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/pantalla2.png)
En esa misma ventana podremmos cambiar el numero de preguntas de los examenes.
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/numPreguntas.PNG)
Tambien cuenta con un menu que permite visualizar una grafica con los examenes realizados.
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/grafica.PNG)
Ese mismo menu contiene una opción que permite añadir una pregunta.
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/addPregunta.PNG)
Y por último el menu cuanta con una opcion que permite regresar al la ventana de seleccion de examenes.
![Screenshot](https://github.com/KingArturo/Examen-de-Conducir/blob/master/image/salir.PNG)

## Pre-requisitos 📋

Para poder ejecutar la aplicación correctamente se necesitara java 11 o superior

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
* TipoExamen
* SelctCantPrePanel
* ConexionDB
* AddPreguntaFrame

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
Clase que crea un Panel en el que se irán mostrnado las preguntas de manera aleatoria junto con sus respuestas, 
tambien dispone de un cronometro de 5 minutos que cuando llega a 0 finaliza el examen. 
El examen se acaba cuando el cronometro llega a 0 o se contestan todas las preguntas. 
Al finalizar el examen saldrá un dialogo informando sobre el resultado del examen.

### PreguntaButton
Clase que extiende de JButton y que contendra el texto de una respuesta a si como si esta es correcta o no. 

### TipoExamen
Clase que extiende de JButton y contiene el nombre de un examen y su id.

### SelctCantPrePanel
Clase que extiende de JPanel y  que contiene un Slider que servira para seleccionar un numero del 5 al 10

### ConexionDB
Clase que se encarga de establecer una conexión con una base de datos y realizar las consultas que se necesite.

### AddPreguntaFrame
Clase que extiende de JFrame y que contiene un formulario con un ComboBox que contiene los examenes existentes asi como 
un campo TextArea y 4 TextFiel en los que se introducira la nueva pregunta que se desee añadir.
Tambien dispone de un boton que abre un explorador de archivo en el que se podrá seleccionar la imagen que 
hace referencia a la pregunta, una vez seleccionado la imagen se copiara a la carpeta resources del proyecto.

## Autor ✒️

* **Arturo Monje Robles** - *Trabajo Inicial* - [KingArturo](https://github.com/KingArturo)

## Expresiones de Gratitud 🎁

* Comenta a otros sobre este proyecto 📢
* Invita una cerveza 🍺 o un café ☕ a alguien del equipo. 
* Da las gracias públicamente 🤓.
* etc.