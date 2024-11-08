package com.example.peliculas.servlet;

import com.example.peliculas.daos.PeliculaDAO;
import com.example.peliculas.beans.Pelicula;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "/PeliculaServlet", urlPatterns = "/PeliculaServlet")
public class PeliculaServlet extends HttpServlet {

    //private PeliculaDAO peliculaDAO = new PeliculaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener lista de películas


        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        PeliculaDAO peliculaDAO = new PeliculaDAO();

        switch (action) {
            case "lista":
                request.setAttribute("listaPeliculas", peliculaDAO.listaPeliculas());
                view = request.getRequestDispatcher("listaPeliculas.jsp");
                view.forward(request, response);
                break;

            case "borrar":
                if (request.getParameter("idPelicula") != null) {
                    String idPeliculaString = request.getParameter("idPelicula");
                    int idPelicula = 0;
                    try {
                        idPelicula = Integer.parseInt(idPeliculaString);
                        peliculaDAO.eliminarPelicula(idPelicula);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("PeliculaServlet?action=lista");
                        return;
                    }

                }

                response.sendRedirect("PeliculaServlet?action=lista");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el término de búsqueda del formulario
        String searchTerm = request.getParameter("searchTerm");

        // Instancia de PeliculaDAO
        PeliculaDAO peliculaDAO = new PeliculaDAO();

        // Realizar la búsqueda y almacenar los resultados en una lista
        request.setAttribute("listaPeliculas", peliculaDAO.buscarPeliculas(searchTerm));

        // Enviar los resultados de la búsqueda a la vista listaPeliculas.jsp
        RequestDispatcher view = request.getRequestDispatcher("listaPeliculas.jsp");
        view.forward(request, response);
    }
}

