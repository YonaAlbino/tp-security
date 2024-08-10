package com.example.tp.integrador.spring.security.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Posteo {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long idPosteo;
    private String titulo;
    private String contenido;
    private Date fecha;

}
