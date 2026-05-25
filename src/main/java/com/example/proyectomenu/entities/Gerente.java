package com.example.proyectomenu.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor // Constructor vacío
public class Gerente extends Persona{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //Indicamos que el ID se genere de forma automática
    private Long idGerente;

    public Gerente(String nombre, String cedula, String telefono, String correo) {
        super(nombre, cedula, telefono, correo);
    }
}
