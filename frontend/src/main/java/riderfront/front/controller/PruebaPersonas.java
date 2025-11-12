package riderfront.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import riderfront.front.dtos.CompraDto;
import riderfront.front.dtos.PersonaDto;
import riderfront.front.webservicespersona.CompraApiPersona;
import riderfront.front.webservicespersona.PersonaApiPersona;

import java.util.List;

@RestController
public class PruebaPersonas {
    @Autowired
    private PersonaApiPersona personaApiPersona;

    @GetMapping("/personas")
    public List<PersonaDto> getPersonas(){
        return personaApiPersona.getPersonas();
    }


    private CompraApiPersona compraApiPersona;

    @GetMapping("/entrega/compras")
    public List<CompraDto> getCompras(){
        return compraApiPersona.getCompras();
    }


}
