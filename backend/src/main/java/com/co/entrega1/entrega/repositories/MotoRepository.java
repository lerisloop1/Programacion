package com.co.entrega1.entrega.repositories;

import java.util.List;
import com.co.entrega1.entrega.entites.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
MotoRepository extends JpaRepository<Moto, String> {
    Optional<Moto> findByMatricula(String matricula);
    List<Moto> findByMarca(String marca);
    List<Moto> findByDisponibilidad(boolean disponibilidad);
}

