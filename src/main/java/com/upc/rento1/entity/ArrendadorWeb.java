package com.upc.rento1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "arrendador_web")
public class ArrendadorWeb {
    @Id
    @Column(name = "id_arrendador", nullable = false)
    private Integer id;

    @Column(name = "nombre2", nullable = false, length = 10)
    private String nombre2;

    @Column(name = "apellido2", nullable = false, length = 15)
    private String apellido2;



}