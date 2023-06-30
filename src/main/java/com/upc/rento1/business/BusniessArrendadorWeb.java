package com.upc.rento1.business;

import com.upc.rento1.entity.ArrendadorWeb;
import com.upc.rento1.repository.RepositoryArrendadorWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BusniessArrendadorWeb {

    @Autowired
    private RepositoryArrendadorWeb repositoryArrendadorWeb;

    @Transactional
    public ArrendadorWeb register(ArrendadorWeb arrendadorWeb){return repositoryArrendadorWeb.save(arrendadorWeb);}

    public List<ArrendadorWeb> listArrendadorWeb(){return repositoryArrendadorWeb.findAll();}

    public ArrendadorWeb actualizarTourist(ArrendadorWeb arrendadorWeb) throws Exception{
        repositoryArrendadorWeb.findById(arrendadorWeb.getId()).orElseThrow(()-> new Exception("No se encontró entidad"));
        return repositoryArrendadorWeb.save(arrendadorWeb);
    }

    public ArrendadorWeb delete(Long codigo) throws Exception{
        ArrendadorWeb arrendadorWeb = repositoryArrendadorWeb.findById(codigo).orElseThrow(()->new Exception("No se encontró entidad"));
        repositoryArrendadorWeb.delete(arrendadorWeb);
        return arrendadorWeb;
    }

}
