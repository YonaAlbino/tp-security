package com.example.tp.integrador.spring.security.repository;

import com.example.tp.integrador.spring.security.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAutorRepository extends JpaRepository<Autor, Long> {
}
