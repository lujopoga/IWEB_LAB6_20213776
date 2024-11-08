package com.example.peliculas.daos;

import com.example.peliculas.beans.Pelicula;
import com.example.peliculas.beans.Genero;

import java.sql.*;
import java.util.ArrayList;

public class PeliculaDAO extends DatabaseConnection{

    public ArrayList<Pelicula> listaPeliculas() {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String sql = "select p.idPelicula, p.titulo, p.director, p.anoPublicacion AS anoPublicacion, p.rating, p.boxOffice, p.idGenero,  g.nombre AS genero from pelicula p\n" +
                "inner join genero g on p.idGenero = g.idGenero order by p.rating DESC, p.boxOffice DESC\n";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                pelicula.setRating(rs.getDouble("rating"));
                pelicula.setBoxOffice(rs.getDouble("boxOffice"));
                // Crear un objeto Genero y asignarlo a la película
                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("idGenero"));
                genero.setNombre(rs.getString("genero"));
                pelicula.setIdGenero(genero);
                peliculas.add(pelicula);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return peliculas;
    }



    public void eliminarPelicula(int idPelicula) {
        String sqlEliminarProtagonistas = "DELETE FROM protagonistas WHERE idPelicula = ?";
        String sqlEliminarPelicula = "DELETE FROM pelicula WHERE idPelicula = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Eliminar primero los protagonistas asociados a la película
            try (PreparedStatement stmtProtagonistas = conn.prepareStatement(sqlEliminarProtagonistas)) {
                stmtProtagonistas.setInt(1, idPelicula);
                stmtProtagonistas.executeUpdate();
            }

            // Luego eliminar la película
            try (PreparedStatement stmtPelicula = conn.prepareStatement(sqlEliminarPelicula)) {
                stmtPelicula.setInt(1, idPelicula);
                stmtPelicula.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pelicula> buscarPeliculas(String searchTerm) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion AS anoPublicacion, p.rating, " +
                "p.boxOffice, p.idGenero, g.nombre AS genero " +
                "FROM pelicula p " +
                "INNER JOIN genero g ON p.idGenero = g.idGenero " +
                "WHERE p.titulo LIKE ? OR p.director LIKE ? " +
                "ORDER BY p.rating DESC, p.boxOffice DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Usar comodines en el término de búsqueda para la consulta SQL
            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                pelicula.setRating(rs.getDouble("rating"));
                pelicula.setBoxOffice(rs.getDouble("boxOffice"));

                // Crear un objeto Genero y asignarlo a la película
                Genero genero = new Genero();
                genero.setNombre(rs.getString("genero"));
                pelicula.setIdGenero(genero);

                peliculas.add(pelicula);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return peliculas;
    }

    public Pelicula obtenerPeliculaPorId(int idPelicula) {
        Pelicula pelicula = null;
        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion AS anoPublicacion, p.rating, " +
                "p.boxOffice, g.nombre AS genero " +
                "FROM pelicula p " +
                "INNER JOIN genero g ON p.idGenero = g.idGenero " +
                "WHERE p.idPelicula = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPelicula);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                pelicula.setRating(rs.getDouble("rating"));
                pelicula.setBoxOffice(rs.getDouble("boxOffice"));

                Genero genero = new Genero();
                genero.setNombre(rs.getString("genero"));
                pelicula.setIdGenero(genero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pelicula;
    }

    public void actualizarPelicula(Pelicula pelicula) {
        String sql = "UPDATE pelicula SET titulo = ?, director = ?, anoPublicacion = ?, rating = ?, boxOffice = ? " +
                "WHERE idPelicula = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getDirector());
            stmt.setInt(3, pelicula.getAnoPublicacion());
            stmt.setDouble(4, pelicula.getRating());
            stmt.setDouble(5, pelicula.getBoxOffice());
            stmt.setInt(6, pelicula.getIdPelicula());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
