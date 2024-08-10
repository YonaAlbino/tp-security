package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.model.Posteo;

import java.util.List;
import java.util.Optional;

public interface IPosteoService {
    public List<Posteo> getAll();
    public Optional<Posteo> findById(Long id);
    public Posteo save(Posteo posteo);
    public Posteo update(Posteo posteo);
    public String deleteById(Long id);
}
