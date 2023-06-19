package com.upc.rento1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "locales_")
public class Locale {
    @Id
    @Column(name = "id_local", nullable = false)
    private Integer id;

    @Column(name = "nombre_local", nullable = false, length = 10)
    private String nombreLocal;

    @Column(name = "precio", nullable = false)
    private Float precio;



}