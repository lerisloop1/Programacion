package com.co.entrega1.entrega.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Entity
    @Table(name = "moto")
    @Data                   // genera getters, setters, toString, equals, hashCode
    @NoArgsConstructor      // constructor vacío
    @AllArgsConstructor     // constructor con todos los campos
    public class Moto {

        @Id
        @Column(name = "id_moto", length = 50, nullable = false)
        private String id;

        @Column(name = "modelo", length = 100, nullable = false)
        private String modelo;
        @Column(name = "matricula", length = 100, nullable = false)
        private String matricula;
        @Column(name = "disponibillidad",nullable = false, columnDefinition = "TINYINT(1)")
        private boolean disponibilidad;

        @Column(name = "marca", length = 100, nullable = false)
        private String marca;
        @Column(name = "precio", nullable = false)
        private int precio;
    }


