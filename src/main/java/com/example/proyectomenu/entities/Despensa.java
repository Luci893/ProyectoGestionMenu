package com.example.proyectomenu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor // Constructor vacío
public class Despensa {

    private Gerente gerente;
    private List<Ingrediente> ingredientes = new ArrayList();

    public Despensa(Gerente gerente) {
        this.gerente = gerente;
    }
}
