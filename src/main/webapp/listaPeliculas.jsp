<%--
  Created by IntelliJ IDEA.
  User: Luis Joaquin Pozo
  Date: 7/11/2024
  Time: 04:19
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.example.peliculas.beans.Pelicula"%>
<%@page import="com.example.peliculas.beans.Genero"%>
<%@page import="com.example.peliculas.beans.Protagonistas"%>
<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="listaPeliculas" type="ArrayList<Pelicula>" scope="request"/>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Películas</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class='mb-3'>Lista de Peliculas</h1>
    <!-- Formulario de Búsqueda -->
    <form action="PeliculaServlet" method="POST" class="form-inline mb-3">
        <input type="text" name="searchTerm" class="form-control mr-2" placeholder="Buscar película...">
        <button type="submit" class="btn btn-primary">Buscar</button>
    </form>

    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>Título</th>
            <th>Director</th>
            <th>Año Publicación</th>
            <th>Rating</th>
            <th>BoxOffice</th>
            <th>Género</th>
            <th>Actores</th>
            <th>Accionable</th>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Pelicula p : listaPeliculas) {
        %>
        <tr>

            <td>
                <a href="<%=request.getContextPath()%>/DetallesServlet?idPelicula=<%=p.getIdPelicula()%>">
                    <%= p.getTitulo() %>
                </a>
            </td>
            <td><%= p.getDirector()%>
            </td>
            <td><%= p.getAnoPublicacion()%>
            </td>
            <td><%= p.getRating()%>/10
            </td>
            <td><%= currencyFormatter.format(p.getBoxOffice()) %>
            </td>
            <td><%= p.getIdGenero().getNombre()%>
            </td>
            <td>
                <a class="btn btn-info"
                   href="<%=request.getContextPath()%>/ActorServlet?idPelicula=<%=p.getIdPelicula()%>">
                    <i class="bi bi-pencil-square">Ver actores</i>
                </a>
            </td>
            <td>
                <a class="btn btn-danger"
                   href="<%=request.getContextPath()%>/PeliculaServlet?action=borrar&idPelicula=<%=p.getIdPelicula()%>"
                    onclick="return confirm('¿Estás seguro de que deseas eliminar esta película?');">
                    <i class="bi bi-trash3">Eliminar</i>
                </a>
            </td>
        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>

