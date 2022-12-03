package com.cc.app.entidades;

public class Platillo {

    private int id;
    private String nombre_platillo;
    private String descripcion;

    public Platillo() {
    }

    public Platillo(int id, String nombre_platillo, String descripcion) {
        this.id = id;
        this.nombre_platillo = nombre_platillo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_platillo() {
        return nombre_platillo;
    }

    public void setNombre_platillo(String nombre_platillo) {
        this.nombre_platillo = nombre_platillo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
