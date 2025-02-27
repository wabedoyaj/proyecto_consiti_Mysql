package com.tutorial.crud.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "producto")
@Getter @Setter
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private float precio;
    public Producto(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
}
