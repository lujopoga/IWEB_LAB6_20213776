package com.example.peliculas.daos;

import com.example.peliculas.beans.Actor;
import com.example.peliculas.beans.Protagonistas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO extends DatabaseConnection{
    public List<Actor> obtenerActoresPorPelicula(int idPelicula) {
        List<Actor> actores = new ArrayList<>();
        String sql = "SELECT a.idActor, a.Nombre, a.Apellido, a.anoNacimiento, a.premioOscar " +
                "FROM Actor a " +
                "JOIN Protagonistas p ON a.idActor = p.idActor " +
                "WHERE p.idPelicula = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPelicula);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Actor actor = new Actor();
                actor.setIdActor(rs.getInt("idActor"));
                actor.setNombre(rs.getString("Nombre"));
                actor.setApellido(rs.getString("Apellido"));
                actor.setAnoNacimiento(rs.getInt("anoNacimiento"));
                actor.setPremioOscar(rs.getBoolean("premioOscar"));
                actores.add(actor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actores;
    }

    public String obtenerTituloPelicula(int idPelicula) {
        String titulo = "";
        String sql = "SELECT titulo FROM Pelicula WHERE idPelicula = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPelicula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                titulo = rs.getString("titulo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titulo;
    }

    public void crearActor(Actor actor, int idPelicula) {
        String sqlActor = "INSERT INTO actor (Nombre, Apellido, anoNacimiento, premioOscar) VALUES (?, ?, ?, ?)";
        String sqlProtagonista = "INSERT INTO protagonistas (idPelicula, idActor) VALUES (?, LAST_INSERT_ID())";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Primero, inserta el actor en la tabla de actores
            try (PreparedStatement stmtActor = conn.prepareStatement(sqlActor)) {
                stmtActor.setString(1, actor.getNombre());
                stmtActor.setString(2, actor.getApellido());
                stmtActor.setInt(3, actor.getAnoNacimiento());
                stmtActor.setBoolean(4, actor.isPremioOscar());
                stmtActor.executeUpdate();

                // Luego, asocia el actor a la película en la tabla "protagonistas" usando LAST_INSERT_ID()
                try (PreparedStatement stmtProtagonista = conn.prepareStatement(sqlProtagonista)) {
                    stmtProtagonista.setInt(1, idPelicula);
                    stmtProtagonista.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

