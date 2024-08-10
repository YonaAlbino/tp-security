package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.model.Autor;
import com.example.tp.integrador.spring.security.repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService implements IAutorService{

    @Autowired
    private IAutorRepository autorRepo;

    @Override
    public List<Autor> getAll() {
        return autorRepo.findAll();
    }

    @Override
    public Optional<Autor> findById(Long id) {
        return autorRepo.findById(id);
    }

    @Override
    public Autor save(Autor autor) {
        return autorRepo.save(autor);
    }

    @Override
    public Autor update(Autor autor) {
        return this.save(autor);
    }

    @Override
    public String deleteById(Long id) {
        autorRepo.deleteById(id);
        return "Autor eliminado";
    }

}
