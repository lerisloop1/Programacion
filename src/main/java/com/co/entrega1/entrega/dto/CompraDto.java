package com.co.entrega1.entrega.dto;

import com.co.entrega1.entrega.entites.Moto;
import com.co.entrega1.entrega.entites.Persona;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
