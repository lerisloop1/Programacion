package com.co.entrega1.entrega.mapper;

import com.co.entrega1.entrega.dto.PersonaDto;
import com.co.entrega1.entrega.entites.Persona;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaDto toDto(Persona persona);
    Persona toEntity(PersonaDto personaDto);
    List<PersonaDto> toDtoList(List<Persona> personas);
}