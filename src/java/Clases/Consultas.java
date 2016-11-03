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
    public ResultSet consultar(Connection con) throws SQLException {
        try {
            
            String query = "SELECT codi,nom FROM alumne";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println("Fin de la consulta.");
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public ResultSet consultarAlumno(Connection con,int cod) throws SQLException {
        try {
            
            String query = "Select al.nom, tu.nom, ass.nom from alumne al, assignatura ass,tutoria tu,tutoriaalumne tual where al.codi=tual.`codiAlumne` and tu.codi=tual.`codiTutoria` and tu.`codiAssignatura`=ass.codi and al.codi="+cod+";";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println("Fin de la consulta.");
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
