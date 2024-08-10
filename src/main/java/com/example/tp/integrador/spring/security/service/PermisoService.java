package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.model.Permiso;
import com.example.tp.integrador.spring.security.repository.IPermisoRepository;
import com.example.tp.integrador.spring.security.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermisoService implements IPermisoService{

    @Autowired
    private IPermisoRepository permisoRepo;

    @Override
    public List<Permiso> getAll() {
        return permisoRepo.findAll();
    }

    @Override
    public Optional<Permiso> findById(Long id) {
        return permisoRepo.findById(id);
    }

    @Override
    public Permiso save(Permiso permiso) {
        return permisoRepo.save(permiso);
    }

    @Override
    public Permiso update(Permiso permiso) {
        return this.save(permiso);
    }

    @Override
    public String deleteById(Long id) {
        permisoRepo.deleteById(id);
        return "Permiso eliminado";
    }
}
