# ServletJDBC

Versión 1.0 de Java ServletJDBC.  
Creado por **Andreu Luna Font**  
Fecha 29/10/2016  
IDE: Netbeans 8.0.2  

Librerias:
* JDK (1.7)
* Apache Tomcat or TomEE
* mysql-connector-java-5.1.40-bin.jar

El proyecto consiste en una web creada con Servlet y .jsp que nos muestre un menú desplegable con unos nombres recogidos de la Base de Datos *conector_ADD* y la tabla *Alumne*. Al elegir el nombre de la persona que queremos consultar y presionar el boton de *Enviar consulta* nos dirige a otra web con dos tablas, una de asignaturas y otra de tutorias del "alumno" seleccionado.
En el Servlet se harán las conexiones con la base de datos y las consultas que se introduciran posteriormente a la web mediante el .jsp con el método de *getAttribut()*.

##Explicación detallada##
El programa inicia el método **doGet()** del Servlet *ServlAlumno*. En este método iniciamos las clases de **conexion**, que tienen métodos para conectarse y desconectarse al servidor de MySQL y la base de datos **conector_add** que es la que utilizamos en este proyecto, y la clase de **consultas** donde tendremos las querys que utilizaremos en el proyecto.

Las Querys que utilizo son:

1. SELECT codi,nom FROM alumne;
  * Para tener una lista de todos los alumnos que tenemos en la base de datos.
  
2. SELECT al.nom, tu.nom, ass.nom FROM alumne al, assignatura ass,tutoria tu,tutoriaalumne tual WHERE al.codi=tual.codiAlumne AND tu.codi=tual.codiTutoria AND tu.codiAssignatura=ass.codi AND al.codi=?;
  * Para tener las listas de Asignaturas y tutorias de un alumno en concreto.

La consulta que utiliza en el **doGet()** es la primera, los valores de la consulta se guardaran en una variable de tipo *ResultSet* con ella generaremos junto al archivo .jsp una web HTML con un formulario que contendra los nombres de los alumnos y su valor a enviar sera el código de estos. Al presionar el botón *Enviar consulta* enviaremos el valor a nuestro método **doPost()** en el Servlet *ServlAlumno*, este recogera el valor en forma de variable y lo enviará a uno de los métodos de la clase **consultas** para ejecutar la segunda consulta expuesta anteriormente. En este caso aparte de retener los resultados enuna variable del tipo *ResultSet* estos los introduciremos a un ArrayList de tipo Alumno con los atributos:
* Nombre alumno
* Nombre Asignaturas
* Nombre Tutorias
