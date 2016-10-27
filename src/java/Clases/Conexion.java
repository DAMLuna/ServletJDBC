/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kerinvel
 */
public class Conexion {
    private Connection conn = null;
    private final String bd = "conector_add";
    private final String url = "jdbc:mysql://localhost:3306/"+bd;
    private final String user = "andreu";
    private final String password = "andreu";

    public Connection doConnection() throws ClassNotFoundException {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, password);            
            if (conn != null) {
                System.out.println("Conectado a " + conn.toString());
            }
            return conn;
        } catch (SQLException e) {
            System.out.println("Conexión no válida url, usuario o clave incorrecta ");
            //if (e.Number == 18456) { // invalid login
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection() {
        try {
            conn.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fallo al cerrar la conexión");
        }
    }
}
