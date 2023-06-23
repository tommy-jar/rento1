package com.upc.rento1.controller;

import com.upc.rento1.busniess.UbicacionServicelmpl;
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
public class UbicacionController {
    @Autowired
    private UbicacionServicelmpl ubicacionServicelmpl;
    Logger logger = LoggerFactory.getLogger(UbicacionController.class);

    @PostMapping("/ubicacion")
    public UbicacionDTO RegistrarUbicacion(@RequestBody UbicacionDTO ubicacionDTO)
    {
        Ubicacion ubicacion;
        try {
            ubicacion = convertToEntity(ubicacionDTO);
            ubicacion = ubicacionServicelmpl.registrar(ubicacion);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay ubicacion");
        }
        return convertToDto(ubicacion);
    }

    @GetMapping("/ubicacion")
    public ResponseEntity<List<UbicacionDTO>> ListarUbicacion(){
        List<Ubicacion> list = ubicacionServicelmpl.obtener();
        List<UbicacionDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<UbicacionDTO>>(listDto,HttpStatus.OK);
    }

    @PutMapping("/ubicacion/{id}")
    public ResponseEntity<UbicacionDTO> ActualizarArrendatariosWeb (@PathVariable(value = "id") Integer id,
                                                                           @RequestBody UbicacionDTO ubicacionDTO)
    {
        Ubicacion ubicacion;
        Ubicacion ubicacionActualizado;
        try {
            ubicacion = convertToEntity(ubicacionDTO);
            ubicacionActualizado = ubicacionServicelmpl.actualizar(id,ubicacion);
            ubicacionDTO = convertToDto(ubicacionActualizado);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible actualizar la ubicacion");
        }
        return new ResponseEntity<UbicacionDTO>(ubicacionDTO, HttpStatus.OK);
    }

    @DeleteMapping("/ubicacion/{id}")
    public  ResponseEntity<UbicacionDTO> BorrarArrendatariosWeb(@PathVariable(value = "id") Integer id){
        Ubicacion ubicacion;
        UbicacionDTO ubicacionDTO;
        try {
            ubicacion = ubicacionServicelmpl.borrarUbicacion(id);
            logger.debug("Ubicacion eliminado");
            ubicacionDTO = convertToDto(ubicacion);
            return new ResponseEntity<UbicacionDTO>(ubicacionDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de eliminación", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede eliminar la ubicacion, sorry crack");
        }
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

}
