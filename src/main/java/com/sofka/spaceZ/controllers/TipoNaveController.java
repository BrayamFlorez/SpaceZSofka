package com.sofka.spaceZ.controllers;

import com.sofka.spaceZ.models.TipoNave;
import com.sofka.spaceZ.services.TipoNaveServices;
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
public class TipoNaveController {
    @Autowired
    private TipoNaveServices service;

    //EndPoint para listar todos los registros de tipo de naves espaciales por metodo get
    @GetMapping("/tnave")
    public List<TipoNave> listar(){
        return service.findAll();
    }

    //EndPoint para crear un nuevo tipo de nave por metodo post
    @PostMapping("/tnave")
    public ResponseEntity<Map<String,Object>> crear(@RequestBody TipoNave tnave){
        TipoNave c = null;
        Map<String,Object> response = new HashMap<>();
        try {
            c = service.save(tnave);
        } catch (DataAccessException e) {
            response.put("mensaje: ", "error al realizar la creacion en base de datos");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje: ", "El tipo de nave se creo con exito");
        response.put("Tipo de Nave: ", tnave);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    //EndPoint para obtener un tipo de nave por su id por el metodo get
    @GetMapping("/tnave/{id}")
    public ResponseEntity<?>show(@PathVariable Long id){
        TipoNave c = null;
        Map<String,Object> response = new HashMap<>();
        try{
            c = service.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje: ", "error al realizar la consulta en base de datos");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (c == null){
            response.put("Mensaje","El tipo de nave con ID: ".concat(" ").concat("No existe"));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TipoNave>(c,HttpStatus.OK);
    }

    //EndPoint para actualizar un tipo de nave por medio de su id
    @PutMapping("tnave/{id}")
    public ResponseEntity<?> update(@RequestBody TipoNave c, @PathVariable Long id){
        TipoNave tNaveActual = service.findById(id);
        TipoNave tNaveUpdate = null;
        Map<String, Object> response = new HashMap<>();

        if (tNaveActual==null){
            response.put("Mensaje","El tipo de nave con ID: ".concat(" ").concat("No existe"));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try {
            tNaveActual.setNombre(c.getNombre());
            tNaveActual.setDescripcion(c.getDescripcion());
            tNaveUpdate= service.save(tNaveActual);

        }catch (DataAccessException e) {
            response.put("mensaje: ", "error al realizar actualizacion en la base de datos");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje","Tipo de nave editado con exito");
        response.put("Tipo de nave",tNaveUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    //EndPoint para eliminar un tipo de nave por medio de su id

    @DeleteMapping("tnave/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try {
            service.delete(id);
        }catch (DataAccessException e) {
            response.put("mensaje: ", "error al realizar eliminacion en la base de datos");
            response.put("error: ", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje","Tipo de nave eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
