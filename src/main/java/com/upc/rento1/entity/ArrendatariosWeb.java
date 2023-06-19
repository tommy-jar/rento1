package com.upc.rento1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "arrendatarios_web")
public class ArrendatariosWeb {
    @Id
    @Column(name = "id_arrendatario", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 10)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 10)
    private String apellido;

    @Column(name = "correo", nullable = false, length = 15)
    private String correo;

    @Column(name = "cuentabancario", nullable = false)
    private Integer cuentabancario;



}