package com.upc.rento1.business;

import com.upc.rento1.entity.ArrendatariosWeb;
import com.upc.rento1.repository.RepositoryArrendatariosWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessArrendatariosWeb {

    @Autowired
    private RepositoryArrendatariosWeb repositoryArrendatariosWeb;

    public ArrendatariosWeb register (ArrendatariosWeb arrendatariosWeb ){
        ArrendatariosWeb arrendatariosWeb1 = repositoryArrendatariosWeb.save(arrendatariosWeb);
        return arrendatariosWeb1;
    }

    public List<ArrendatariosWeb>  obtenerReporte()
    {
        return  repositoryArrendatariosWeb.findAll();
    }

    public  ArrendatariosWeb  actualizarDatos(Integer id, ArrendatariosWeb arrendatariosWeb)
    {
        ArrendatariosWeb postulantAntiguo = repositoryArrendatariosWeb.findById(id).get();
        arrendatariosWeb.setId(id);
        return  repositoryArrendatariosWeb.save(arrendatariosWeb);
    }
    public ArrendatariosWeb borrar(Integer codigo) throws  Exception {
        ArrendatariosWeb arrendatariosWeb = repositoryArrendatariosWeb.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        repositoryArrendatariosWeb.delete(arrendatariosWeb);
        return arrendatariosWeb;
    }
}
