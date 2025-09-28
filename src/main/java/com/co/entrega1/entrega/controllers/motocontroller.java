package com.co.entrega1.entrega.controllers;

import com.co.entrega1.entrega.entites.Moto;
import com.co.entrega1.entrega.services.MotoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/motos")
public class motocontroller {

    @Autowired
    private MotoServices service;

    //CREATE
    @PostMapping
    public ResponseEntity<Moto> crearMoto(@RequestBody Moto moto) {
        Moto nueva = service.crearMoto(moto);
        return ResponseEntity.ok(nueva);
    }

    //READ: listar todas motos
    @GetMapping
    public ResponseEntity<List<Moto>> listarMotos() {
        return ResponseEntity.ok(service.listarMotos());
    }

    //READ: buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Moto> buscarPorId(@PathVariable String id) {
        Optional<Moto> persona = service.buscarPorId(id);
        return persona.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable String id) {
        boolean eliminado = service.eliminarMoto(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}