package com.upc.rento1.controller;

import com.upc.rento1.business.BusniessArrendadorWeb;
import com.upc.rento1.dto.ArrendadorWebDTO;
import com.upc.rento1.entity.ArrendadorWeb;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ControllerArrendadorWeb {
    @Autowired
    public BusniessArrendadorWeb busniessArrendadorWeb;

    Logger logger = LoggerFactory.getLogger(ControllerArrendadorWeb.class);

    @PostMapping("/arrendadorWeb")
    public ResponseEntity<ArrendadorWebDTO> register(@RequestBody ArrendadorWebDTO arrendadorWebDTO){
        ArrendadorWeb arrendadorWeb = convertToEntity(arrendadorWebDTO);
        arrendadorWeb = busniessArrendadorWeb.register(arrendadorWeb);
        arrendadorWebDTO = convertToDto(arrendadorWeb);
        return  new ResponseEntity<ArrendadorWebDTO>(arrendadorWebDTO, HttpStatus.OK);
    }
    @GetMapping("/arrendador")
    public ResponseEntity<List<ArrendadorWebDTO>> listArrendaor(){
        List<ArrendadorWeb> list = busniessArrendadorWeb.listArrendadorWeb();
        List<ArrendadorWebDTO> listDTO = convertToLisDto(list);
        return new ResponseEntity<List<ArrendadorWebDTO>>(listDTO, HttpStatus.OK);
    }

    @PutMapping("/arrendador")
    public  ResponseEntity<ArrendadorWebDTO> actualizarArrendador(@RequestBody ArrendadorWebDTO arrendadorWebDetalle){
        ArrendadorWebDTO arrendadorWebDTO;
        ArrendadorWeb arrendadorWeb;
        try{
            arrendadorWeb = convertToEntity(arrendadorWebDetalle);
            logger.debug("Actualizando lista");
            arrendadorWeb = busniessArrendadorWeb.actualizarTourist(arrendadorWeb);
            logger.debug("Turista Actualizado");
            arrendadorWebDTO = convertToDto(arrendadorWeb);
            return new ResponseEntity<ArrendadorWebDTO>(arrendadorWebDTO, HttpStatus.OK);

        }catch (Exception e){
            logger.error("Error de Actualización", e);
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "no se pudo actualizar, sorry");
        }
    }

    @DeleteMapping("/arrendador/{codigo}")
    public ResponseEntity<ArrendadorWebDTO> borrarArrendador(@PathVariable(value = "codigo") Long codigo){
        ArrendadorWeb arrendadorWeb;
        ArrendadorWebDTO arrendadorWebDTO;
        try {
            arrendadorWeb = busniessArrendadorWeb.delete(codigo);
            logger.debug("Eliminando objeto");
            arrendadorWebDTO = convertToDto(arrendadorWeb);
            return new ResponseEntity<ArrendadorWebDTO>(arrendadorWebDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de Eliminación ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }


    private ArrendadorWebDTO convertToDto(ArrendadorWeb arrendadorWeb) {
        ModelMapper modelMapper = new ModelMapper();
        ArrendadorWebDTO arrendadorWebDTO = modelMapper.map(arrendadorWeb, ArrendadorWebDTO.class);
        return arrendadorWebDTO;
    }

    private ArrendadorWeb convertToEntity(ArrendadorWebDTO arrendadorWebDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ArrendadorWeb post = modelMapper.map(arrendadorWebDTO, ArrendadorWeb.class);
        return post;
    }

    private List<ArrendadorWebDTO> convertToLisDto(List<ArrendadorWeb> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
