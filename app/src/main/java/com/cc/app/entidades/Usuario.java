package com.cc.app.entidades;

public class Usuario {

    private int id;
    private String nombre;
    private String correo_electronico;
    private int admin;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String correo_electronico, int admin) {
        this.id = id;
        this.nombre = nombre;
        this.correo_electronico = correo_electronico;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public int isAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
