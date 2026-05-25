package com.example.proyectomenu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor // Constructor vacío
public class Ingrediente {

    @Id //Marcamos la primary key de nuestra Base de Datos
    @GeneratedValue(strategy= GenerationType.IDENTITY) //Indicamos que el ID se genere de forma automática
    private Long idIngrediente;
    @NotBlank(message = "La descripción del ingrediente no puede estar vacía")
    private String descripcion;

    @ManyToOne //Muchos ingredientes, 1 receta
    @JoinColumn(name = "idReceta")
    @JsonIgnore // evita que genere un json repetitivo ignorando este atributo
    private Receta receta;

    public Ingrediente(String descripcion) {
        this.descripcion = descripcion;
    }
}
