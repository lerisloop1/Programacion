package riderfront.front.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompraDto {
    private Long id;
    private LocalDate fechaCompra;
    private String motoMatricula;
    private String personaId;

}
