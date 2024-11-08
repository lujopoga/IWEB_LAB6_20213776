<%--
  Created by IntelliJ IDEA.
  User: Luis Joaquin Pozo
  Date: 8/11/2024
  Time: 01:06
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.example.peliculas.beans.Actor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="listaActores" type="java.util.ArrayList" scope="request" />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title><%= request.getAttribute("tituloPelicula") %></title>
</head>
<body>
<h1><%= request.getAttribute("tituloPelicula") %></h1>

<table border="1">
  <tr>
    <th>idActor</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Año Nacimiento</th>
    <th>Ganador Premio Oscar</th>
  </tr>
  <%
    for (Actor actor : (ArrayList<Actor>) request.getAttribute("listaActores")) {
  %>
  <tr>
    <td><%= actor.getIdActor() %></td>
    <td><%= actor.getNombre() %></td>
    <td><%= actor.getApellido() %></td>
    <td><%= actor.getAnoNacimiento() %></td>
    <td><%= actor.isPremioOscar() %></td>
  </tr>
  <%
    }
  %>
</table>

<a href="ActorServlet?action=crear&idPelicula=<%= request.getAttribute("pelicula") %>">Crear Actor</a>
</body>
</html>

