package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.model.Rol;
import com.example.tp.integrador.spring.security.repository.IPosteoRepository;
import com.example.tp.integrador.spring.security.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IRolService{

    @Autowired
    private IRolRepository rolRepo;

    @Override
    public List<Rol> getAll() {
        return rolRepo.findAll();
    }

    @Override
    public Optional<Rol> findById(Long id) {
        return rolRepo.findById(id);
    }

    @Override
    public Rol save(Rol rol) {
        return rolRepo.save(rol);
    }

    @Override
    public Rol update(Rol rol) {
        return this.save(rol);
    }

    @Override
    public String deleteById(Long id) {
        rolRepo.deleteById(id);
        return "El rol fue eliminado";
    }

    @Override
    public Rol findRolByName(String name) {
        return rolRepo.findRolBynombreRol(name);
    }
}
