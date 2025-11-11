package riderfront.front.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    private String id;
    private String nombre;
    private String apellido;
}

