package com.upc.rento1.services;

import com.upc.rento1.entity.Ubicacion;

import java.util.List;


public interface IUbicacionService {
    public void registrar(Ubicacion ubicacion);

    public void eliminar(Long idUbicacion);

    public void actualizar (Long idUbicacion);

    List<Ubicacion> list();
}
