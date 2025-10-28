package com.co.entrega1.entrega.dto;
import lombok.*;
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
