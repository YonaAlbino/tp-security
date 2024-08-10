package com.example.tp.integrador.spring.security.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoguinRequestDTO(@NotBlank String nombreUsuario,
                                   @NotBlank String contrasenia) {
}
