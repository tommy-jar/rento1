package com.upc.rento1.services;

import com.upc.rento1.entity.ArrendatariosWeb;

import java.util.List;

public interface IArrendatariosWebService {

    public void insertar(ArrendatariosWeb arrendatariosWeb);
    public void eliminar(Long idArrendatariosWeb);
    public void actualizar (Long idArrendatariosWeb);
    List<ArrendatariosWeb> listar();

}
