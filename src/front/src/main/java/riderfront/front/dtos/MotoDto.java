package riderfront.front.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class MotoDto {
    private String id;
    private String modelo;
    private String matricula;
    private boolean disponibilidad;
    private String marca;
    private int precio;
}
