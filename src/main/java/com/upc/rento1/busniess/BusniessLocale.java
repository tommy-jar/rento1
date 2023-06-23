package com.upc.rento1.busniess;

import com.upc.rento1.entity.Locale;
import com.upc.rento1.repostory.LocalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BusniessLocale {
    @Autowired
    private LocalesRepository repositorylocales;

    public Locale register(Locale locale){
        Locale locale1 = repositorylocales.save(locale);
        return locale1;
    }
    public List<Locale> obtenerReporte()
    {
        return  repositorylocales.findAll();
    }

    public  Locale  actualizarDatos(Integer id, Locale locale)
    {
        Locale localeAntiguo = repositorylocales.findById(id).get();
        locale.setId(Math.toIntExact(id));
        return  repositorylocales.save(locale);
    }
    public Locale borrar(Integer codigo) throws  Exception {
        Locale locale = repositorylocales.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        repositorylocales.delete(locale);
        return locale;
    }
}
