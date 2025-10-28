package com.co.entrega1.entrega.controllers;

import com.co.entrega1.entrega.dto.CompraDto;
import com.co.entrega1.entrega.entites.Compra;
import com.co.entrega1.entrega.services.CompraServices;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/compras")
public class compracontroller {

    private final CompraServices compraservices;

    public compracontroller(CompraServices compraservices) {
        this.compraservices = compraservices;
    }

    @PostMapping("/comprar")
    public CompraDto comprarMoto(@RequestParam String personaId, @RequestParam String matriculaMoto) {
        CompraDto compra =new CompraDto();
        compra.setPersonaId(personaId);
        compra.setMotoMatricula(matriculaMoto);
        return compraservices.comprarMoto(compra);
    }

    @GetMapping("/fecha/{fecha}")
    public List<CompraDto> listarPorFecha(@PathVariable("fecha") String fechaCompra) {
        LocalDate fechaLocal = LocalDate.parse(fechaCompra);
        return compraservices.listarPorFecha(fechaLocal);
    }
    @GetMapping("/compras")
    public List<CompraDto> listarTodasLasCompras() {
        return compraservices.listarCompras();
    }

}
