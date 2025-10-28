package com.co.entrega1.entrega.mapper;

import com.co.entrega1.entrega.dto.MotoDto;
import com.co.entrega1.entrega.entites.Moto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MotoMapper {
    MotoDto toDto(Moto moto);
    Moto toEntity(MotoDto motoDto);
    List<MotoDto> toDtoList(List<Moto> motos);
}