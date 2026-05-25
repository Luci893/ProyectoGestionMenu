package com.example.proyectomenu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor // Constructor vacío
public class Alimento{

    @Id //Marcamos la primary key de nuestra Base de Datos
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idAlimento;
    @NotBlank(message = "El nombre del alimento no puede estar vacío.")
    @Pattern(regexp = "[a-zA-Z\\s]+",
    message = "El nombre del alimento debe contener solo letras")
    private String nombre;
    @Positive(message = "El precio debe ser positivo")
    private double precio;

    //Relacion N a 1 con menu
    @ManyToOne
    @JoinColumn(name="idMenu")
    @JsonIgnore // evita que genere un json repetitivo ignorando este atributo
    private Menu menu;

    @Valid
    //Relacion 1 a 1 con receta
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idReceta")
    private Receta receta;

    public Alimento(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
}
