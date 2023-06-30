package com.upc.rento1.business;

import com.upc.rento1.entity.Descripcion;
import com.upc.rento1.repository.DescripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BusinessDescripcion {

    @Autowired
    private DescripcionRepository descripcionRepository;

    @Transactional
    public  Descripcion registrar_descripcion(Descripcion descripcion1){
        Descripcion descripcion = descripcionRepository.save(descripcion1);

        return descripcion;
    }

    public  List<Descripcion> list_description(){
        return descripcionRepository.findAll();
    }

    public Descripcion actualizar_descripcion(Integer id, Descripcion descripcion1){
        Descripcion descripcion2 = descripcionRepository.findById(id).get();
        descripcion1.setId(Math.toIntExact(id));
        return descripcionRepository.save(descripcion1);
    }
}
