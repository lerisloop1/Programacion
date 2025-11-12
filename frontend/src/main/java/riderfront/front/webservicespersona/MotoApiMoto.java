package riderfront.front.webservicespersona;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import riderfront.front.dtos.MotoDto;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class MotoApiMoto {
    private final RestTemplate restTemplate;

    @Value("${api.personas.base-url:http://localhost:8080/entrega/api}")
    private String baseUrl;

    public MotoApiMoto(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MotoDto> getMotos(){
        String url = baseUrl + "/motos";
        MotoDto[] motoArray = restTemplate.getForObject(url, MotoDto[].class);
        return List.of(motoArray);
    }
}
