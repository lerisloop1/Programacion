package com.co.entrega1.entrega.services;

import com.co.entrega1.entrega.entites.Persona;
import com.co.entrega1.entrega.repositories.PersonasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServices {

    private PersonasRepository repo;

    public void PersonaService(PersonasRepository repo) {
        this.repo = repo;
    }

    public PersonaServices(PersonasRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Persona crearPersona(Persona persona) {
        return repo.save(persona);
    }

    // READ: todas las personas
    public List<Persona> listarPersonas() {
        return repo.findAll();
    }

    // READ: persona por id
    public Optional<Persona> buscarPorId(String id) {
        return repo.findById(id);
    }

    // UPDATE
    public Persona actualizarPersona(String id, Persona datosActualizados) {
        return repo.findById(id).map(p -> {
            p.setNombre(datosActualizados.getNombre());
            p.setApellido(datosActualizados.getApellido());
            return repo.save(p);
        }).orElse(null);
    }

    // DELETE
    public boolean eliminarPersona(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}

