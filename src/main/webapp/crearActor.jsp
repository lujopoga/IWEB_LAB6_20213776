<%--
  Created by IntelliJ IDEA.
  User: Luis Joaquin Pozo
  Date: 7/11/2024
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.peliculas.beans.Actor" %>
<%@ page import="com.example.peliculas.beans.Pelicula" %>
<jsp:useBean id="pelicula" type="com.example.peliculas.beans.Pelicula" scope="request" />

<!DOCTYPE html>
<html>
<head>
    <title>Registrar Actor</title>
</head>
<body>
<h2>Registrar Actor para la Película: <%= request.getAttribute("tituloPelicula") %></h2>
<form action="ActorServlet" method="POST">
    <input type="hidden" name="idPelicula" value="<%= request.getAttribute("idPelicula") %>">
    <table>
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="nombre" required></td>
        </tr>
        <tr>
            <td>Apellido:</td>
            <td><input type="text" name="apellido" required></td>
        </tr>
        <tr>
            <td>Año de Nacimiento:</td>
            <td><input type="number" name="anoNacimiento" required></td>
        </tr>
        <tr>
            <td>Ganador de Premio Oscar:</td>
            <td>
                <select name="premioOscar" required>
                    <option value="true">Sí</option>
                    <option value="false">No</option>
                </select>
            </td>
        </tr>
    </table>
    <button type="submit">Registrar Actor</button>
</form>
</body>
</html>
