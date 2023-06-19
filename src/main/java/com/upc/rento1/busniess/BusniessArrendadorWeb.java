package com.upc.rento1.busniess;

import com.upc.rento1.entity.ArrendadorWeb;
import com.upc.rento1.repostory.RepositoryBusniessArrendadorWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BusniessArrendadorWeb {
    @Autowired
    private RepositoryBusniessArrendadorWeb repositoryBusniessArrendadorWeb;

    @Transactional
    public ArrendadorWeb register(ArrendadorWeb arrendadorWeb){return repositoryBusniessArrendadorWeb.save(arrendadorWeb);}

}
