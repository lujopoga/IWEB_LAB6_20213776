<%--
  Created by IntelliJ IDEA.
  User: Luis Joaquin Pozo
  Date: 7/11/2024
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>

<%@page import="com.example.peliculas.beans.Pelicula"%>
<%@page import="com.example.peliculas.beans.Genero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="pelicula" type="com.example.peliculas.beans.Pelicula" scope="request" />

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Detalles de la Película</title>
</head>
<body>
<h1>Película Número <%= pelicula.getIdPelicula() %></h1>

<form action="DetallesServlet" method="post">
  <input type="hidden" name="idPelicula" value="<%= pelicula.getIdPelicula() %>">
  <table border="1">
    <tr>
      <td>Título</td>
      <td><input type="text" name="titulo" value="<%= pelicula.getTitulo() %>"></td>
    </tr>
    <tr>
      <td>Director</td>
      <td><input type="text" name="director" value="<%= pelicula.getDirector() %>"></td>
    </tr>
    <tr>
      <td>Año Publicación</td>
      <td><input type="text" name="anoPublicacion" value="<%= pelicula.getAnoPublicacion() %>"></td>
    </tr>
    <tr>
      <td>Rating</td>
      <td><input type="text" name="rating" value="<%= pelicula.getRating() %>"></td>
    </tr>
    <tr>
      <td>BoxOffice</td>
      <td><input type="text" name="boxOffice" value="<%= pelicula.getBoxOffice() %>"></td>
    </tr>
    <tr>
      <td>Género</td>
      <td><%= pelicula.getIdGenero().getNombre() %></td>
    </tr>
    <tr>
      <td>Actores</td>
      <td><a href="<%=request.getContextPath()%>/ActorServlet?idPelicula=<%=pelicula.getIdPelicula()%>">Ver Actores</a></td>
    </tr>
  </table>
  <button type="submit">Guardar Película</button>
</form>
</body>
</html>

