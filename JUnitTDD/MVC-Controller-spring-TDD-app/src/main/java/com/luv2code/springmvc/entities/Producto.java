package com.luv2code.springmvc.entities;

import javax.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private double precio;

    // Getters y setters
}