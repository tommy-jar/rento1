package com.upc.rento1.controller;


import com.upc.rento1.business.BusinessUbicacion;
import com.upc.rento1.dto.UbicacionDTO;
import com.upc.rento1.entity.Ubicacion;
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
public class ControllerUbicacion {
    @Autowired
    private BusinessUbicacion businessUbicacion;
    Logger logger = LoggerFactory.getLogger(UbicacionDTO.class);

    @PostMapping("/ubicacion")
    public ResponseEntity<UbicacionDTO> register(@RequestBody UbicacionDTO ubicacionDTO){
        Ubicacion ubicacion = convertToEntity(ubicacionDTO);
        ubicacion = businessUbicacion.registrar(ubicacion);
        ubicacionDTO = convertToDto(ubicacion);
        return  new ResponseEntity<UbicacionDTO>(ubicacionDTO, HttpStatus.OK);
    }

    @GetMapping("/ubicacion")
    public ResponseEntity<List<UbicacionDTO>> ListarUbicacion(){
        List<Ubicacion> list = businessUbicacion.obtener();
        List<UbicacionDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<UbicacionDTO>>(listDto,HttpStatus.OK);
    }


    private UbicacionDTO convertToDto(Ubicacion ubicacion) {
        ModelMapper modelMapper = new ModelMapper();
        UbicacionDTO ubicacionDTO = modelMapper.map(ubicacion, UbicacionDTO.class);
        return ubicacionDTO;
    }

    private Ubicacion convertToEntity(UbicacionDTO ubicacionDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Ubicacion post = modelMapper.map(ubicacionDTO, Ubicacion.class);
        return post;
    }
    private List<UbicacionDTO> convertToLisDto(List<Ubicacion> list) {
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/ubicacion")
    public ResponseEntity<UbicacionDTO> actualizarUbicacion(@RequestBody UbicacionDTO ubicacionDetalle){
        UbicacionDTO ubicacionDTO;
        Ubicacion ubicacion;
        try{
            ubicacion = convertToEntity(ubicacionDetalle);
            logger.debug("Actualizando lista");
            ubicacion = businessUbicacion.actualizar(ubicacion);
            logger.debug("Turista Actualizado");
            ubicacionDTO = convertToDto(ubicacion);
            return new ResponseEntity<UbicacionDTO>(ubicacionDTO, HttpStatus.OK);

        }catch (Exception e){
            logger.error("Error de Actualización", e);
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "no se pudo actualizar, sorry");
        }
    }
}
