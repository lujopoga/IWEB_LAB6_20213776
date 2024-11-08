package com.example.peliculas.beans;

public class Pelicula {
    private int idPelicula;
    private String titulo;
    private String director;
    private int anoPublicacion;
    private double rating;
    private double boxOffice;
    private Genero idGenero;

    // Constructor vacío
    public Pelicula() {}

    // Constructor con parámetros
    public Pelicula(int idPelicula, String titulo, String director, int anoPublicacion, double rating, double boxOffice, Genero idGenero) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.director = director;
        this.anoPublicacion = anoPublicacion;
        this.rating = rating;
        this.boxOffice = boxOffice;
        this.idGenero = idGenero;
    }

    // Getters y Setters
    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(double boxOffice) {
        this.boxOffice = boxOffice;
    }

    public Genero getIdGenero() { // Cambiado a Genero
        return idGenero;
    }

    public void setIdGenero(Genero idGenero) { // Cambiado a Genero
        this.idGenero = idGenero;
    }
}
