<%-- 
    Document   : result
    Created on : 25-oct-2016, 5:20:48
    Author     : Kerinvel
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Alumnos"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%ArrayList<Alumnos> alum = (ArrayList) request.getAttribute("listaAssTut");%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Alumnos - Resultado</title>
        <style>
            table, th, td {
                border: 1px solid black;
                text-align: center;
            }
            h1{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Lista de Assignaturas y tutorias de <%=alum.get(0).getNomAl()%></h1>
        <center>
            <table style="width:20%">
                <tr>
                    <th>Asignaturas</th>
                </tr>
                <%for (int i = 0; i < alum.size(); i++) {

                %>
                <tr>
                    <td><%=alum.get(i).getNomAss()%></td>
                </tr>
                <%}%>
            </table></br>
            <table style="width:20%">
                <tr>
                    <th>Tutorias</th>
                </tr>
                <%for (int i = 0; i < alum.size(); i++) {

                %>
                <tr>
                    <td><%=alum.get(i).getNomTut()%></td>
                </tr>
                <%}%>
            </table>
        </center>
    </body>
</html>
