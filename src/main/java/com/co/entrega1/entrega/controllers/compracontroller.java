package com.co.entrega1.entrega.controllers;

import com.co.entrega1.entrega.entites.Compra;
import com.co.entrega1.entrega.services.CompraServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class compracontroller {

    private final CompraServices compraservices;

    public compracontroller(CompraServices compraservices) {
        this.compraservices = compraservices;
    }

    @PostMapping("/comprar")
    public Compra comprarMoto(@RequestParam String personaId, @RequestParam String matriculaMoto) {
        return compraservices.comprarMoto(personaId, matriculaMoto);
    }
}
