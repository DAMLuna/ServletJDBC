/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Kerinvel
 */
public class Consultas {
    public static ResultSet consultar(Connection con) throws SQLException {
        try {
            
            String query = "SELECT codi,nom FROM alumne";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                System.out.println("Codigo: "+rs.getString("codi")+" - Nombre: "+rs.getString("nom"));
            }
            System.out.println("Fin de la consulta.");
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
