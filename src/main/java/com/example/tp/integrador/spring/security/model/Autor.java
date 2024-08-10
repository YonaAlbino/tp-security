package com.example.tp.integrador.spring.security.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Autor extends Persona {
    private String cantidadLibros;

    @OneToOne
    @JoinColumn(name = "id_usuario",
                referencedColumnName = "idUsuario")
    private Usuario unUsuario;

    @OneToMany()
    private List<Posteo> listaPosteos = new ArrayList<>();
}
