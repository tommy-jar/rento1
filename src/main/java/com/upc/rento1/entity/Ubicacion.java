package com.upc.rento1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ciudad;

    private String distrito;

    private String direccion;

    public Ubicacion(Long id, String ciudad, String distrito, String direccion) {
        this.id = id;
        this.ciudad = ciudad;
        this.distrito = distrito;
        this.direccion = direccion;
    }

    public Ubicacion() {
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "id=" + id +
                ", ciudad='" + ciudad + '\'' +
                ", distrito='" + distrito + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
