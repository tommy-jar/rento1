package com.upc.rento1.business;

import com.upc.rento1.entity.Ubicacion;
import com.upc.rento1.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BusinessUbicacion {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Transactional
    public Ubicacion registrar(Ubicacion ubicacion){ return ubicacionRepository.save(ubicacion);}

    public List<Ubicacion> obtener(){ return  ubicacionRepository.findAll();}


    public  Ubicacion  actualizar(Ubicacion ubicacion) throws Exception{
        ubicacionRepository.findById(ubicacion.getId()).orElseThrow(()-> new Exception("No se encontró entidad"));
        return ubicacionRepository.save(ubicacion);
    }

}
