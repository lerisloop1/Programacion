package com.co.entrega1.entrega.controllers;

import com.co.entrega1.entrega.dto.PersonaDto;
import com.co.entrega1.entrega.entites.Persona;
import com.co.entrega1.entrega.services.PersonaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class personacontroller {

    @Autowired
    private PersonaServices service;

    //Endpoint de prueba
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    //CREATE
    @PostMapping
    public ResponseEntity<PersonaDto> crearPersona(@RequestBody PersonaDto persona) {
        PersonaDto nueva = service.crearPersona(persona);
        return ResponseEntity.ok(nueva);
    }

    //READ: listar todas
    @GetMapping
    public ResponseEntity<List<PersonaDto>> listarPersonas() {
        return ResponseEntity.ok(service.listarPersonas());
    }

    //READ: buscar por id
    @GetMapping("/buscar/{id}")
    public ResponseEntity<PersonaDto> buscarPorId(@PathVariable String id) {
        Optional<PersonaDto> persona = service.buscarPorId(id);
        return persona.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //UPDATE
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PersonaDto> actualizarPersona(@PathVariable String id,
                                                     @RequestBody PersonaDto datos) {
        PersonaDto actualizada = service.actualizarPersona(id, datos);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    //DELETE
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable String id) {
        boolean eliminado = service.eliminarPersona(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
