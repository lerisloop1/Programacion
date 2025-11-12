package com.co.entrega1.entrega.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MotoDto {
    private String id;
    private String modelo;
    private String matricula;
    private boolean disponibilidad;
    private String marca;
    private int precio;
}
