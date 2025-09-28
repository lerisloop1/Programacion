package com.co.entrega1.entrega.services;

import com.co.entrega1.entrega.entites.Moto;
import com.co.entrega1.entrega.repositories.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoServices {

    private MotoRepository repom;

    public void motoservice(MotoRepository repo) {
        this.repom = repo;
    }

    public MotoServices(MotoRepository repo) {
        this.repom = repo;
    }

    // CREATE
    public Moto crearMoto(Moto moto) {
        return repom.save(moto);

    }

    // READ: todas las motos
    public List<Moto> listarMotos() {
        return repom.findAll();
    }

    // READ: motos por  por id
    public Optional<Moto> buscarPorId(String id) {
        return repom.findById(id);
    }


    // DELETE
    public boolean eliminarMoto(String id) {
        if (repom.existsById(id)) {
            repom.deleteById(id);
            return true;
        }
        return false;
    }
}