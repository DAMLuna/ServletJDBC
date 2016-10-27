<%-- 
    Document   : index
    Created on : 25-oct-2016, 2:45:48
    Author     : Kerinvel
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Alumnos</title>
    </head>
    <body>
        <h1 style="text-align: center">Lista de Alumnos</h1>
        <div style="text-align: center;margin-top: 10%">
            <form action="" method="post">
                <select name="codAlumno">
                    <option value="0">Elige un alumno...</option>
                    <%ResultSet rs = (ResultSet) request.getAttribute("listaAlumnos");
                        while (rs.next()) {%>
                    <option  value="<%=rs.getInt("codi")%>"><%=rs.getString("nom")%></option>
                    <%}%>
                </select> 
                <input type="submit">
            </form>
        </div>
    </body>
</html>
