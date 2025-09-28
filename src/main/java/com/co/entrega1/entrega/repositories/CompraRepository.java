package com.co.entrega1.entrega.repositories;

import com.co.entrega1.entrega.entites.Compra;
import com.co.entrega1.entrega.entites.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
