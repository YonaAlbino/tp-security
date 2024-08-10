package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.model.Autor;

import java.util.List;
import java.util.Optional;

public interface IAutorService {
    public List<Autor> getAll();
    public Optional<Autor> findById(Long id);
    public Autor save(Autor autor);
    public Autor update(Autor autor);
    public String deleteById(Long id);
}
