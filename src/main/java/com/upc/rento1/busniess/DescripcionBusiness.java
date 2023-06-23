package com.upc.rento1.busniess;

import com.upc.rento1.entity.Descripcion;
import com.upc.rento1.repostory.DescripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DescripcionBusiness {

    @Autowired
    private DescripcionRepository descripcionRepository;

    @Transactional
    public Descripcion registrar_descripcion(Descripcion descripcion){
        return descripcionRepository.save(descripcion);
    }

    public List<Descripcion> list_description(){
        return descripcionRepository.findAll();
    }

    public Descripcion actualizar_descripcion(Integer id, Descripcion descripcion){
        Descripcion descripcion1 = descripcionRepository.findById(id).get();
        descripcion.setId(Math.toIntExact(id));
        return descripcionRepository.save(descripcion);
    }



}
