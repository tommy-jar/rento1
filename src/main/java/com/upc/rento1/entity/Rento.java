package com.upc.rento1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rento_")
public class Rento {
    @Id
    @Column(name = "locales__id_local", nullable = false)
    private Integer id;





}