package com.sofka.spaceZ.controllers;


import com.sofka.spaceZ.models.Nave;
import com.sofka.spaceZ.services.NaveServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class NaveController {
    @Autowired
    private NaveServices service;
    @GetMapping("/nave")
    public List<Nave> listar(){
        return service.findAll();
    }
    @PostMapping("/nave")
    public ResponseEntity<Map<String,Object>> crear(@RequestBody Nave nave){
        Nave c = null;
        Map<String,Object> response = new HashMap<>();
        try {
            c = service.save(nave);
        } catch (DataAccessException e) {
            response.put("mensaje: ", "error al realizar la creacion en base de datos");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje: ", "La nave se creo con exito");
        response.put("Nave: ", nave);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/nave/{id}")
    public ResponseEntity<?>show(@PathVariable Long id){
        Nave c = null;
        Map<String,Object> response = new HashMap<>();
        try{
            c = service.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje: ", "error al realizar la consulta en base de datos");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (c == null){
            response.put("Mensaje","La nave con ID: ".concat(" ").concat("No existe"));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Nave>(c,HttpStatus.OK);
    }
    @PutMapping("nave/{id}")
    public ResponseEntity<?> update(@RequestBody Nave c, @PathVariable Long id){
        Nave naveActual = service.findById(id);
        Nave naveUpdate = null;
        Map<String, Object> response = new HashMap<>();

        if (naveActual==null){
            response.put("Mensaje","La nave con ID: ".concat(" ").concat("No existe"));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try {
            naveActual.setNombre(c.getNombre());
            naveActual.setTipo(c.getTipo());
            naveUpdate= service.save(naveActual);

        }catch (DataAccessException e) {
            response.put("mensaje: ", "error al realizar actualizacion en la base de datos");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje","nave editada con exito");
        response.put("Nave",naveUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("nave/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try {
            service.delete(id);
        }catch (DataAccessException e) {
            response.put("mensaje: ", "error al realizar eliminacion en la base de datos");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje","nave eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}

