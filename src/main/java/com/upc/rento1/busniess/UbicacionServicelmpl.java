package com.upc.rento1.busniess;


import com.upc.rento1.entity.Ubicacion;
import com.upc.rento1.services.IUbicacionService;
import com.upc.rento1.repostory.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionServicelmpl{

    @Autowired
    private UbicacionRepository UbicacionRepositoy;

    public Ubicacion registrar (Ubicacion ubicacion ){
        Ubicacion ubicacion1 = UbicacionRepositoy.save(ubicacion);
        return ubicacion1;
    }
    public List<Ubicacion>  obtener(){ return  UbicacionRepositoy.findAll();}

    public Ubicacion borrarUbicacion(Integer id) throws  Exception {
        Ubicacion ubicacion = UbicacionRepositoy.findById(id).orElseThrow(() -> new Exception("No se encontró la id de la ubicacion"));
        UbicacionRepositoy.delete(ubicacion);
        return ubicacion;
    }

    public  Ubicacion  actualizar(Integer id, Ubicacion ubicacion)
    {
        Ubicacion ubicacion1 = UbicacionRepositoy.findById(id).get();
        ubicacion.setId(id);
        return  UbicacionRepositoy.save(ubicacion);
    }
}
