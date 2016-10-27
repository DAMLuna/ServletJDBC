/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Clases.Alumnos;
import Clases.Conexion;
import Clases.Consultas;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kerinvel
 */
public class ServlAlumnos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista Alumnos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 style=\"text-align:center\">No has seleccionado ningún alumno</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/conector_add", "andreu", "andreu");
            String query = "SELECT codi,nom FROM alumne";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            request.setAttribute("listaAlumnos", rs);
            RequestDispatcher a = request.getRequestDispatcher("/index.jsp");
            a.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServlAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServlAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cod = Integer.parseInt(request.getParameter("codAlumno"));
        if (cod == 0) {
            processRequest(request, response);
        } else {
            try {
                ResultSet rs;
                int i;
                ArrayList<Alumnos> alum = new ArrayList();

                Class.forName("org.gjt.mm.mysql.Driver");
                Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/conector_add", "andreu", "andreu");
                String query = "Select al.nom, tu.nom, ass.nom from alumne al, assignatura ass,tutoria tu,tutoriaalumne tual where al.codi=tual.`codiAlumne` and tu.codi=tual.`codiTutoria` and tu.`codiAssignatura`=ass.codi and al.codi=?;";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, cod);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Alumnos a = new Alumnos(rs.getString(1), rs.getString(2), rs.getString(3));
                    alum.add(a);
                }
                request.setAttribute("listaAssTut", alum);
                RequestDispatcher a = request.getRequestDispatcher("/result.jsp");
                a.forward(request, response);
                pst.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServlAlumnos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ServlAlumnos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
