package com.upc.rento1.services;

import com.upc.rento1.entity.Locale;
import java.util.List;
public interface ILocaleService {
    public void insertar(Locale arrendatariosWeb);
    public void eliminar(Long idLocale);
    public void actualizar (Long idLocale);
    List<Locale> listar();
}
