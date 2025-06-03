package com.bch.api.rest.entities;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE", unique = true, nullable = false)
    private String nombre;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos útiles
    @Override
    public String toString() {
        return "Rol{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               '}';
    }
}