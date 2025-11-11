package riderfront.front.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Moto {

        private String id;
        private String modelo;
        private String matricula;
        private boolean disponibilidad;
        private String marca;
        private int precio;
    }


