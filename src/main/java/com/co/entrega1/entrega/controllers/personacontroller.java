package com.co.entrega1.entrega.controllers;

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
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        Persona nueva = service.crearPersona(persona);
        return ResponseEntity.ok(nueva);
    }

    //READ: listar todas
    @GetMapping
    public ResponseEntity<List<Persona>> listarPersonas() {
        return ResponseEntity.ok(service.listarPersonas());
    }

    //READ: buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Persona> buscarPorId(@PathVariable String id) {
        Optional<Persona> persona = service.buscarPorId(id);
        return persona.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable String id,
                                                     @RequestBody Persona datos) {
        Persona actualizada = service.actualizarPersona(id, datos);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable String id) {
        boolean eliminado = service.eliminarPersona(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
