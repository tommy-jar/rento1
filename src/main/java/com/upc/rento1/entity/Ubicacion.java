package com.upc.rento1.entity;

import com.upc.rento1.entity.Locale;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ubicacion_")
public class Ubicacion {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ciudad", nullable = false, length = 10)
    private String ciudad;

    @Column(name = "distrito", nullable = false, length = 10)
    private String distrito;

    @Column(name = "direccion_", nullable = false, length = 20)
    private String direccion;



}