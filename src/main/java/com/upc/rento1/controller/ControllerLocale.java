package com.upc.rento1.controller;

import com.upc.rento1.business.BusniessLocal;
import com.upc.rento1.dto.LocalDTO;
import com.upc.rento1.entity.Local;
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
public class ControllerLocale {
    @Autowired
    private BusniessLocal busniessLocale;

    Logger logger = LoggerFactory.getLogger(ControllerLocale.class);

    @PostMapping("/local")
    public LocalDTO Registrar(@RequestBody LocalDTO localesDTO){
        Local locale;
        try{
            locale =convertToEntity(localesDTO);
            locale =busniessLocale.register(locale);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO hay");
        }
            return convertToDto(locale);
    }

    @GetMapping("/local")
    public ResponseEntity<List<LocalDTO>> listarlocale(){
        List<Local> list = busniessLocale.list();
        List<LocalDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<LocalDTO>>(listDto,HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<LocalDTO> ActualizarLocale (@PathVariable(value = "id") Long id,
                                                      @RequestBody LocalDTO LocaleDTO)
    {
        Local Locale;
        Local LocaleActualizado;
        try {
            Locale = convertToEntity(LocaleDTO);
            LocaleActualizado = busniessLocale.actualizarDatos(id,Locale);
            LocaleDTO = convertToDto(LocaleActualizado);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible actualizar el centro de salud");
        }
        return new ResponseEntity<LocalDTO>(LocaleDTO, HttpStatus.OK);
    }


    
    private LocalDTO convertToDto(Local locale) {
        ModelMapper modelMapper = new ModelMapper();
        LocalDTO localesDTO = modelMapper.map(locale, LocalDTO.class);
        return localesDTO;
    }
    private Local convertToEntity(LocalDTO localesDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Local post = modelMapper.map(localesDTO, Local.class);
        return post;
    }
    private List<LocalDTO> convertToLisDto(List<Local> list) {
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{codigo}")
    public  ResponseEntity<LocalDTO> Borrarlocale(@PathVariable(value = "codigo") Long codigo){
        Local locale;
        LocalDTO LocalesDTO;
        try {
            locale = busniessLocale.borrar(codigo);
            logger.debug("Postulate Eliminado");
            LocalesDTO = convertToDto(locale);
            return new ResponseEntity<LocalDTO>(LocalesDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de eliminación", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede eliminar, sorry");
        }
    }
}
