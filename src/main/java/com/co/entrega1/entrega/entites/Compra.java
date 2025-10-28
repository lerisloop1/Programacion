package com.co.entrega1.entrega.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compra {
//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Persona
    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id", nullable = false)
    private Persona persona;

    // Relación con Moto
    @ManyToOne
    @JoinColumn(name = "moto_matricula", referencedColumnName = "matricula",nullable = false)
    private Moto moto;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;
}
