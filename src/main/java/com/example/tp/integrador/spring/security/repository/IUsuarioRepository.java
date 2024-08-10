package com.example.tp.integrador.spring.security.repository;

import com.example.tp.integrador.spring.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findUserEntityByusername(String username);
}
