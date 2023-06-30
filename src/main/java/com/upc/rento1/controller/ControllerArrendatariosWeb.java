package com.upc.rento1.controller;

import com.upc.rento1.business.BusinessArrendatariosWeb;
import com.upc.rento1.dto.ArrendatariosWebDTO;
import com.upc.rento1.entity.ArrendatariosWeb;
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
public class ControllerArrendatariosWeb {
    @Autowired
    private BusinessArrendatariosWeb businessArrendatariosWeb;
    Logger logger = LoggerFactory.getLogger(ControllerArrendatariosWeb.class);

    @PostMapping("/arrendatariosWeb")
    public ArrendatariosWebDTO RegistrarArrendatariosWeb(@RequestBody ArrendatariosWebDTO arrendatariosWebDTO)
    {
        ArrendatariosWeb arrendatariosWeb;
        try {
            arrendatariosWeb = convertToEntity(arrendatariosWebDTO);
            arrendatariosWeb = businessArrendatariosWeb.register(arrendatariosWeb);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO hay");
        }
        return convertToDto(arrendatariosWeb);
    }

    @GetMapping("/arrendatariosWeb")
    public ResponseEntity<List<ArrendatariosWebDTO>> ListarArrendatariosWeb(){
        List<ArrendatariosWeb> list = businessArrendatariosWeb.obtenerReporte();
        List<ArrendatariosWebDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<ArrendatariosWebDTO>>(listDto,HttpStatus.OK);
    }

    @PutMapping("/arrendatariosWeb/{id}")
    public ResponseEntity<ArrendatariosWebDTO> ActualizarArrendatariosWeb (@PathVariable(value = "id") Integer id,
                                                @RequestBody ArrendatariosWebDTO arrendatariosWebDTO)
    {
        ArrendatariosWeb arrendatariosWeb;
        ArrendatariosWeb arrendatariosWebActualizado;
        try {
            arrendatariosWeb = convertToEntity(arrendatariosWebDTO);
            arrendatariosWebActualizado = businessArrendatariosWeb.actualizarDatos(id,arrendatariosWeb);
            arrendatariosWebDTO = convertToDto(arrendatariosWebActualizado);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible actualizar el centro de salud");
        }
        return new ResponseEntity<ArrendatariosWebDTO>(arrendatariosWebDTO, HttpStatus.OK);
    }

    @DeleteMapping("/arrendatariosWeb/{codigo}")
    public  ResponseEntity<ArrendatariosWebDTO> BorrarArrendatariosWeb(@PathVariable(value = "codigo") Integer codigo){
        ArrendatariosWeb arrendatariosWeb;
        ArrendatariosWebDTO arrendatariosWebDTO;
        try {
            arrendatariosWeb = businessArrendatariosWeb.borrar(codigo);
            logger.debug("Postulante Eliminado");
            arrendatariosWebDTO = convertToDto(arrendatariosWeb);
            return new ResponseEntity<ArrendatariosWebDTO>(arrendatariosWebDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de eliminación", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede eliminar, sorry");
        }
    }


    private ArrendatariosWebDTO convertToDto(ArrendatariosWeb arrendatariosWeb) {
        ModelMapper modelMapper = new ModelMapper();
        ArrendatariosWebDTO arrendatariosWebDTO = modelMapper.map(arrendatariosWeb, ArrendatariosWebDTO.class);
        return arrendatariosWebDTO;
    }

    private ArrendatariosWeb convertToEntity(ArrendatariosWebDTO arrendatariosWebDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ArrendatariosWeb post = modelMapper.map(arrendatariosWebDTO, ArrendatariosWeb.class);
        return post;
    }
    private List<ArrendatariosWebDTO> convertToLisDto(List<ArrendatariosWeb> list) {
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



}

