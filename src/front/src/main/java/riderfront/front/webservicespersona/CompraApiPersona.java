package riderfront.front.webservicespersona;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import riderfront.front.dtos.CompraDto;

import java.util.List;

@FeignClient(name="compra-api-persona", url="${api.personas.base-url:http://localhost:8080/entrega}")
public interface CompraApiPersona {

    @GetMapping("/compras")
    static List<CompraDto> getCompras();

    List<CompraDto> getCompras();
}
