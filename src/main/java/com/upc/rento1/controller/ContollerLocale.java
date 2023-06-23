package com.upc.rento1.controller;

import com.upc.rento1.busniess.BusniessLocale;
import com.upc.rento1.dto.LocalesDTO;
import com.upc.rento1.entity.Locale;
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
public class ContollerLocale {
    @Autowired
    private BusniessLocale busniessLocale;
    Logger logger = LoggerFactory.getLogger(ContollerLocale.class);

    @PostMapping("/locale")
    public LocalesDTO RegistraLocale(@RequestBody LocalesDTO localesDTO) {
        Locale locale;
        try{
            locale = convertToEntity(localesDTO);
            locale = busniessLocale.register(locale);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO hay");
        }
        return convertToDto(locale);
    }

    @GetMapping("/locale")
    public ResponseEntity<List<LocalesDTO>> ListarLocale(){
        List<Locale> list = busniessLocale.obtenerReporte();
        List<LocalesDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<LocalesDTO>>(listDto,HttpStatus.OK);
    }

    @PutMapping("/locale/{id}")
    public ResponseEntity<LocalesDTO> ActualizarLocales (@PathVariable(value = "id") Integer id,
                                                                           @RequestBody LocalesDTO localesDTO)
    {
        Locale locales;
        Locale localesActualizado;
        try {
            locales = convertToEntity(localesDTO);
            localesActualizado = busniessLocale.actualizarDatos(id,locales);
            localesDTO = convertToDto(localesActualizado);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible actualizar el centro de salud");
        }
        return new ResponseEntity<LocalesDTO>(localesDTO, HttpStatus.OK);
    }

    @DeleteMapping("/locale/{codigo}")
    public  ResponseEntity<LocalesDTO> BorrarLocales(@PathVariable(value = "codigo") Integer codigo){
        Locale locale;
        LocalesDTO localesDTO;
        try {
            locale = busniessLocale.borrar(codigo);
            logger.debug("Local Eliminado");
            localesDTO = convertToDto(locale);
            return new ResponseEntity<LocalesDTO>(localesDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de eliminación", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede eliminar");
        }
    }

    private LocalesDTO convertToDto(Locale locale) {
        ModelMapper modelMapper = new ModelMapper();
        LocalesDTO localesDTO = modelMapper.map(locale, LocalesDTO.class);
        return localesDTO;
    }
    private Locale convertToEntity(LocalesDTO localesDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Locale post = modelMapper.map(localesDTO, Locale.class);
        return post;
    }
    private List<LocalesDTO> convertToLisDto(List<Locale> list) {
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}

