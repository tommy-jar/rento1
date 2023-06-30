package com.upc.rento1.business;

import com.upc.rento1.entity.Local;
import com.upc.rento1.repository.LocalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class BusniessLocal {
    @Autowired
    private LocalesRepository localesRepository;
    @Transactional
    public Local register(Local locale){return localesRepository.save(locale);}

    public List<Local>  list(){return localesRepository.findAll();}

    public Local actualizarDatos(Long id, Local locale) throws Exception {
        localesRepository.findById(locale.getId()).orElseThrow(()-> new Exception("No se encontró entidad"));
        return localesRepository.save(locale);
    }

    public Local borrar(Long codigo) throws  Exception {
        Local locale = localesRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        localesRepository.delete(locale);
        return locale;
    }
}
