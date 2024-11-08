package com.example.peliculas.servlet;

import com.example.peliculas.beans.Pelicula;
import com.example.peliculas.daos.PeliculaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DetallesServlet")
public class DetallesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el ID de la película desde el parámetro
        String idPeliculaStr = request.getParameter("idPelicula");
        int idPelicula = Integer.parseInt(idPeliculaStr);

        // Obtener detalles de la película usando el DAO
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        Pelicula pelicula = peliculaDAO.obtenerPeliculaPorId(idPelicula);

        // Pasar los detalles de la película a la vista
        request.setAttribute("pelicula", pelicula);
        RequestDispatcher view = request.getRequestDispatcher("viewPelicula.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        String titulo = request.getParameter("titulo");
        String director = request.getParameter("director");
        int anoPublicacion = Integer.parseInt(request.getParameter("anoPublicacion"));
        double rating = Double.parseDouble(request.getParameter("rating"));
        double boxOffice = Double.parseDouble(request.getParameter("boxOffice"));

        PeliculaDAO peliculaDAO = new PeliculaDAO();
        Pelicula pelicula = new Pelicula(idPelicula, titulo, director, anoPublicacion, rating, boxOffice, null);

        peliculaDAO.actualizarPelicula(pelicula);
        response.sendRedirect("PeliculaServlet?action=lista");
    }

}

