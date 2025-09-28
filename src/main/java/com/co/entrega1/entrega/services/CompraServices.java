package com.co.entrega1.entrega.services;

import com.co.entrega1.entrega.entites.Compra;
import com.co.entrega1.entrega.entites.Moto;
import com.co.entrega1.entrega.entites.Persona;
import com.co.entrega1.entrega.repositories.CompraRepository;
import com.co.entrega1.entrega.repositories.MotoRepository;
import com.co.entrega1.entrega.repositories.PersonasRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CompraServices {

    private final CompraRepository comprarepository;
    private final PersonasRepository personasrepository;
    private final MotoRepository motorepository;

    public CompraServices(CompraRepository comprarepository,
                          PersonasRepository personaRepository,
                          MotoRepository motorepository) {
        this.comprarepository = comprarepository;
        this.personasrepository = personaRepository;
        this.motorepository = motorepository;
    }

    public Compra comprarMoto(String personaId, String matriculaMoto) {
        Persona persona = personasrepository.findById(personaId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        Moto moto = motorepository.findByMatricula(matriculaMoto)
                .orElseThrow(() -> new RuntimeException("Moto no encontrada"));

        Compra compra = new Compra();
        compra.setPersona(persona);
        compra.setMoto(moto);
        compra.setFechaCompra(LocalDateTime.now());

        return comprarepository.save(compra);
    }
}