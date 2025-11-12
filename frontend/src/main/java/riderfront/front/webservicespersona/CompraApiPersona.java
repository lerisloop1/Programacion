package riderfront.front.webservicespersona;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import riderfront.front.dtos.CompraDto;

import java.util.List;

@FeignClient(name="compra-api-persona", url="${api.personas.base-url:http://localhost:8080/entrega/api/compras}")
public interface CompraApiPersona {


    @GetMapping("/compras")
    List<CompraDto> getCompras();
    @PostMapping("/comprar")
    CompraDto comprarMoto(@RequestBody CompraDto compra);

}
