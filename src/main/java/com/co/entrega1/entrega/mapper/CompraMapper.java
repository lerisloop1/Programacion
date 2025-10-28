package com.co.entrega1.entrega.mapper;
import com.co.entrega1.entrega.entites.Compra;
import com.co.entrega1.entrega.dto.CompraDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CompraMapper {

    @Mapping(source = "persona.id", target = "personaId")
    @Mapping(source = "moto.matricula", target = "motoMatricula")
    CompraDto toDto(Compra compra);

    @Mapping(source = "personaId", target = "persona.id")
    @Mapping(source = "motoMatricula", target = "moto.matricula")
    Compra toEntity(CompraDto compraDto);
    List<CompraDto> toDtoList(List<Compra> compras);
}