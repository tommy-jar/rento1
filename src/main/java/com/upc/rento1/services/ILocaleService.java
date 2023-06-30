package com.upc.rento1.services;

import com.upc.rento1.entity.Local;
import java.util.List;
public interface ILocaleService {
    public void insertar(Local arrendatariosWeb);
    public void eliminar(Long idLocale);
    public void actualizar (Long idLocale);
    List<Local> listar();
}
