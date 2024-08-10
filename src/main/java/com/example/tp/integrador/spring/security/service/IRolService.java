package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.model.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    public List<Rol> getAll();
    public Optional<Rol> findById(Long id);
    public Rol save(Rol rol);
    public Rol update(Rol rol);
    public String deleteById(Long id);
    public Rol findRolByName(String name);
}
