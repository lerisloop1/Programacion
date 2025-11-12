package com.co.entrega1.entrega.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persona")
@Data                   // genera getters, setters, toString, equals, hashCode
@NoArgsConstructor      // constructor vacío
@AllArgsConstructor     // constructor con todos los campos
public class Persona {

    @Id
    @Column(name = "id", length = 50, nullable = false)
    private String id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 100, nullable = false)
    private String apellido;
}

