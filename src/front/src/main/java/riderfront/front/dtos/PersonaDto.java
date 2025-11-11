package riderfront.front.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {
    private String id;
    private String nombre;
    private String apellido;
}
