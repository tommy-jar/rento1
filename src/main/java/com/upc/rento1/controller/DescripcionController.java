package com.upc.rento1.controller;


import com.upc.rento1.busniess.DescripcionBusiness;
import com.upc.rento1.dto.DescripcionDTO;
import com.upc.rento1.entity.Descripcion;
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
@RequestMapping("/descripcion")
public class DescripcionController {

    @Autowired
    private DescripcionBusiness descripcionBusiness;
    Logger logger= LoggerFactory.getLogger(DescripcionController.class);

    @PostMapping("/registrar")
    public DescripcionDTO Registrar_descripcion(@RequestBody DescripcionDTO descripcionDTO){
        Descripcion descripcion;
        try {
            descripcion = convertToEntity(descripcionDTO);
            descripcion = descripcionBusiness.registrar_descripcion(descripcion);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado");
        }
        return convertToDto(descripcion);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DescripcionDTO>> Listar_descripciones(){
        List<Descripcion> list = descripcionBusiness.list_description();
        List<DescripcionDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<DescripcionDTO>>(listDto,HttpStatus.OK);
    }



    private DescripcionDTO convertToDto(Descripcion descripcion) {
        ModelMapper modelMapper = new ModelMapper();
        DescripcionDTO descripcionDTO = modelMapper.map(descripcion, DescripcionDTO.class);
        return descripcionDTO;
    }

    private Descripcion convertToEntity(DescripcionDTO descripcionDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Descripcion post = modelMapper.map(descripcionDTO, Descripcion.class);
        return post;
    }
    private List<DescripcionDTO> convertToLisDto(List<Descripcion> list) {
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}