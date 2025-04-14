package com.bch.api.rest.dto;

public class Usuarios {

    private String nombre;
    private String apellido;
    //private String fecha_creacion;

    // Getters y setters para cada atributo

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
    
   /* public String getFecha_creacion() {
        return fecha_creacion;
    }

    // Cambiar el nombre del setter a setFechaCreacion
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }*/
}
