package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.model.Permiso;

import java.util.List;
import java.util.Optional;

public interface IPermisoService {
    public List<Permiso> getAll();
    public Optional<Permiso> findById(Long id);
    public Permiso save(Permiso permiso);
    public Permiso update(Permiso permiso);
    public String deleteById(Long id);
}
