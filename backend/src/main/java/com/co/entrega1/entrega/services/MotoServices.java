package com.co.entrega1.entrega.services;

import com.co.entrega1.entrega.dto.MotoDto;
import com.co.entrega1.entrega.entites.Moto;
import com.co.entrega1.entrega.mapper.MotoMapper;
import com.co.entrega1.entrega.repositories.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoServices {
    private final MotoRepository motoRepository;
    private final MotoMapper motoMapper;

    public MotoServices(MotoRepository repom, MotoMapper motoMapper) {
        this.motoRepository = repom;
        this.motoMapper = motoMapper;
    }

    // meter la moto
    public MotoDto crearMoto(MotoDto motoDto) {
        Moto moto = motoMapper.toEntity(motoDto);
        Moto guardada = motoRepository.save(moto);
        return motoMapper.toDto(guardada);
    }

    // Mostrar todas las motos
    public List<MotoDto> listarMotos() {
        return motoMapper.toDtoList(motoRepository.findAll());
    }

    // Buscar por ID de la moto
    public Optional<MotoDto> buscarPorId(String id) {
        return motoRepository.findById(id).map(motoMapper::toDto);
    }

    // Borrar una moto
    public boolean eliminarMoto(String id) {
        if (motoRepository.existsById(id)) {
            motoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Listar por marca
    public List<MotoDto> listarPorMarca(String marca) {
        return motoMapper.toDtoList(motoRepository.findByMarca(marca));
    }

    // Listar por disponibilidad
    public List<MotoDto> listarPorDisponibilidad(boolean disponibilidad) {
        return motoMapper.toDtoList(motoRepository.findByDisponibilidad(disponibilidad));
    }
}