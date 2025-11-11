package riderfront.front.webservicespersona;

import riderfront.front.dtos.PersonaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PersonaApiPersona {
    private final RestTemplate restTemplate;

    @Value("${api.personas.base-url:http://localhost:8080/entrega}")
    private String baseUrl;

    public PersonaApiPersona(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PersonaDto> getPersonas(){
        String url = baseUrl + "/personas";
        PersonaDto[] personasArray = restTemplate.getForObject(url, PersonaDto[].class);
        return List.of(personasArray);
    }

}
