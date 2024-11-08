package com.example.peliculas.servlet;

import com.example.peliculas.beans.Actor;
import com.example.peliculas.daos.ActorDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ActorServlet")
public class ActorServlet extends HttpServlet {
    private ActorDAO actorDAO = new ActorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "ver" : request.getParameter("action");
        int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        switch (action) {
            case "ver":

                List<Actor> listaActores = actorDAO.obtenerActoresPorPelicula(idPelicula);
                request.setAttribute("listaActores", listaActores);

                String tituloPelicula = actorDAO.obtenerTituloPelicula(idPelicula);
                request.setAttribute("tituloPelicula", tituloPelicula);

                request.setAttribute("idPelicula", idPelicula);

                RequestDispatcher view = request.getRequestDispatcher("listaActores.jsp");
                view.forward(request, response);
                break;

            case "crear":
                request.setAttribute("idPelicula", idPelicula);
                RequestDispatcher crearView = request.getRequestDispatcher("crearActor.jsp");
                crearView.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int anoNacimiento = Integer.parseInt(request.getParameter("anoNacimiento"));
        boolean premioOscar = Boolean.parseBoolean(request.getParameter("premioOscar"));
        int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));

        // Crear el objeto Actor
        Actor actor = new Actor();
        actor.setNombre(nombre);
        actor.setApellido(apellido);
        actor.setAnoNacimiento(anoNacimiento);
        actor.setPremioOscar(premioOscar);

        // Guardar el actor en la base de datos y asociarlo a la película
        actorDAO.crearActor(actor, idPelicula);

        // Redirigir a la lista de actores de la película actualizada
        response.sendRedirect("ActorServlet?idPelicula=" + idPelicula + "&action=ver");
    }
}

