# ServletJDBC
Versión 1.0 de Java ServletJDBC.

Creado por **Andreu Luna Font**

El programa inicia el método **doGet()** del Servlet *ServlAlumno*. En este método iniciamos las clases de **conexion**, que tienen métodos para conectarse y desconectarse al servidor de MySQL y la base de datos **conector_add** que es la que utilizamos en este proyecto, y la clase de **consultas** donde tendremos las querys que utilizaremos en el proyecto.

Las Querys que utilizo son:

1. SELECT codi,nom FROM alumne;
  ..1. Para tener una lista de todos los alumnos que tenemos en la base de datos.
  
2. SELECT al.nom, tu.nom, ass.nom FROM alumne al, assignatura ass,tutoria tu,tutoriaalumne tual WHERE al.codi=tual.codiAlumne AND tu.codi=tual.codiTutoria AND tu.codiAssignatura=ass.codi AND al.codi=?;
  ..1. Para tener las listas de Asignaturas y tutorias de un alumno en concreto.

