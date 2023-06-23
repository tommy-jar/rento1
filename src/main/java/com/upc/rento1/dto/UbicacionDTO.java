package com.upc.rento1.dto;

import javax.persistence.Column;

import com.upc.rento1.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UbicacionDTO {
    private Long idUbicacion;
    private String ciudadUbicacion;
    private String distritoUbicacion;
    private String direccionUbicacion;


}
