package com.example.peliculas.beans;

public class Actor {
    private int idActor;
    private String nombre;
    private String apellido;
    private int anoNacimiento;
    private boolean premioOscar;

    // Constructor vacío
    public Actor() {}

    // Constructor con parámetros
    public Actor(int idActor, String nombre, String apellido, int anoNacimiento, boolean premioOscar) {
        this.idActor = idActor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.anoNacimiento = anoNacimiento;
        this.premioOscar = premioOscar;
    }

    // Getters y Setters
    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public boolean isPremioOscar() {
        return premioOscar;
    }

    public void setPremioOscar(boolean premioOscar) {
        this.premioOscar = premioOscar;
    }
}

