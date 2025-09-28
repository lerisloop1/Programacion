package com.co.entrega1.entrega.repositories;

import com.co.entrega1.entrega.entites.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonasRepository extends JpaRepository<Persona, String> {
}
