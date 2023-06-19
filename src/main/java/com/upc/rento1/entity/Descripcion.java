package com.upc.rento1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "descripcion_")
public class Descripcion {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "general", nullable = false, length = 10)
    private String general;

    @Column(name = "fotos", nullable = false)
    private byte[] fotos;

    @Column(name = "comentarios", nullable = false, length = 100)
    private String comentarios;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;



}