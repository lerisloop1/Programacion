package riderfront.front.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compra {


    private Long id;
    private Persona persona;
    private Moto moto;
    private LocalDate fechaCompra;
}
