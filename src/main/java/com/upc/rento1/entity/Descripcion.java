package com.upc.rento1.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="descripcion")
public class Descripcion {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "general", nullable = false, length = 200)
    private String general;


    @Column(name = "comentarios", nullable = false, length = 100)
    private String comentarios;

    public Descripcion() {
    }

    public Descripcion(Integer id, String general, String comentarios) {
        this.id = id;

        this.general = general;

        this.comentarios = comentarios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }



    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "descripcion{" +
                "id=" + id +
                ", general='" + general + '\'' +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }

}
