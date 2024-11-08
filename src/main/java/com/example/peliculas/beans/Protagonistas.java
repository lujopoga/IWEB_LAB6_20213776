package com.example.peliculas.beans;

public class Protagonistas {
    private int idPelicula;
    private int idActor;

    // Constructor vacío
    public Protagonistas() {}

    // Constructor con parámetros
    public Protagonistas(int idPelicula, int idActor) {
        this.idPelicula = idPelicula;
        this.idActor = idActor;
    }

    // Getters y Setters
    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }
}

