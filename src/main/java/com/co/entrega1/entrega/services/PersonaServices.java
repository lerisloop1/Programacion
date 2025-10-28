
package com.co.entrega1.entrega.services;

import com.co.entrega1.entrega.dto.PersonaDto;
import com.co.entrega1.entrega.entites.Persona;
import com.co.entrega1.entrega.mapper.PersonaMapper;
import com.co.entrega1.entrega.repositories.PersonasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    public class PersonaServices {

        private final PersonasRepository personasRepository;
        private final PersonaMapper personaMapper;

        public PersonaServices(PersonasRepository personasRepository, PersonaMapper personaMapper) {
            this.personasRepository = personasRepository;
            this.personaMapper = personaMapper;
        }

        // CREATE
        public PersonaDto crearPersona(PersonaDto personaDto) {
            Persona persona = personaMapper.toEntity(personaDto);
            Persona guardada = personasRepository.save(persona);
            return personaMapper.toDto(guardada);
        }

        // READ: todas las personas
        public List<PersonaDto> listarPersonas() {
            return personaMapper.toDtoList(personasRepository.findAll());
        }

        // READ: persona por id
        public Optional<PersonaDto> buscarPorId(String id) {
            return personasRepository.findById(id).map(personaMapper::toDto);
        }

        // UPDATE
        public PersonaDto actualizarPersona(String id, PersonaDto datosActualizados) {
            Optional<Persona> personaOpt = personasRepository.findById(id);
            if (personaOpt.isPresent()) {
                Persona persona = personaOpt.get();
                persona.setNombre(datosActualizados.getNombre());
                persona.setApellido(datosActualizados.getApellido());
                Persona actualizada = personasRepository.save(persona);
                return personaMapper.toDto(actualizada);
            }
            return null;
        }

        // DELETE
        public boolean eliminarPersona(String id) {
            if (personasRepository.existsById(id)) {
                personasRepository.deleteById(id);
                return true;
            }
            return false;
        }
    }


