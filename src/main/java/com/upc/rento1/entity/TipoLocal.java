package com.upc.rento1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tipo_local_")
public class TipoLocal {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;



}