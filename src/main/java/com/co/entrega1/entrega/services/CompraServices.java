package com.co.entrega1.entrega.services;

import com.co.entrega1.entrega.dto.CompraDto;
import com.co.entrega1.entrega.entites.Compra;
import com.co.entrega1.entrega.entites.Moto;
import com.co.entrega1.entrega.entites.Persona;
import com.co.entrega1.entrega.mapper.CompraMapper;
import com.co.entrega1.entrega.repositories.CompraRepository;
import com.co.entrega1.entrega.repositories.MotoRepository;
import com.co.entrega1.entrega.repositories.PersonasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraServices {
    @Autowired
    private final CompraRepository comprarepository;
    @Autowired
    private final PersonasRepository personasrepository;
    @Autowired
    private final MotoRepository motorepository;
    @Autowired
    private final CompraMapper compraMapper;

    public CompraServices(CompraRepository comprarepository,
                          PersonasRepository personaRepository,
                          MotoRepository motorepository,
                          CompraMapper compraMapper) {
        this.comprarepository = comprarepository;
        this.personasrepository = personaRepository;
        this.motorepository = motorepository;
        this.compraMapper = compraMapper;
    }

    public CompraDto comprarMoto(CompraDto compraDto) {
        Persona persona = personasrepository.findById(compraDto.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Moto moto = motorepository.findById(compraDto.getMotoMatricula())
                .orElseThrow(() -> new RuntimeException("Moto no encontrada"));

        // Verificamos si la moto ya fue vendida
        if (!moto.isDisponibilidad()) {
            throw new RuntimeException("La moto no está disponible para la compra");
        }

        // Creamos la entidad Compra usando el mapper
        Compra compra = compraMapper.toEntity(compraDto);
        compra.setPersona(persona);
        compra.setMoto(moto);
        compra.setFechaCompra(LocalDate.now());

        Compra saved = comprarepository.save(compra);

        // Actualizamos la disponibilidad de la moto
        moto.setDisponibilidad(false);
        motorepository.save(moto);

        return compraMapper.toDto(saved);
    }

    public List<CompraDto> listarPorFecha(LocalDate fechaCompra) {
        return comprarepository.findByFechaCompra(fechaCompra)
                .stream()
                .map(compraMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<CompraDto> listarCompras() {
        return comprarepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Compra::getFechaCompra).reversed())
                .map(compraMapper::toDto)
                .collect(Collectors.toList());
    }
}