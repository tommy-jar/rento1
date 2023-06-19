package com.upc.rento1.controller;

import com.upc.rento1.busniess.BusniessArrendadorWeb;
import com.upc.rento1.dto.ArrendadorWebDTO;
import com.upc.rento1.entity.ArrendadorWeb;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
