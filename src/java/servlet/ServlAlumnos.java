/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Clases.Alumnos;
import Clases.Conexion;
import Clases.Consultas;
import Clases.GeneradorXml;
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
        Conexion con = new Conexion();
        Consultas q = new Consultas();
        try {
            ResultSet rs = q.consultar(con.doConnection());
            request.setAttribute("listaAlumnos", rs);
            RequestDispatcher a = request.getRequestDispatcher("/index.jsp");
            a.forward(request, response);
            con.closeConnection();
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
        //Comprueba si hemos dado un valor valido.
        if (cod == 0) {
            processRequest(request, response);
        } else {
            try {
                Conexion con = new Conexion();
                Consultas q = new Consultas();
                GeneradorXml gx=new GeneradorXml();
                int i;
                ArrayList<Alumnos> alum = new ArrayList();
                ResultSet rs = q.consultarAlumno(con.doConnection(), cod);
                while (rs.next()) {
                    Alumnos a = new Alumnos(rs.getString(1), rs.getString(2), rs.getString(3));
                    alum.add(a);
                }
                gx.prepararArchivo(alum);
                request.setAttribute("listaAssTut", alum);
                RequestDispatcher a = request.getRequestDispatcher("/result.jsp");
                a.forward(request, response);
                con.closeConnection();
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
