package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.model.Posteo;
import com.example.tp.integrador.spring.security.repository.IPosteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosteoService implements IPosteoService{

    @Autowired
    private IPosteoRepository posteRepo;

    @Override
    public List<Posteo> getAll() {
        return posteRepo.findAll();
    }

    @Override
    public Optional<Posteo> findById(Long id) {
        return posteRepo.findById(id);
    }

    @Override
    public Posteo save(Posteo posteo) {
        return posteRepo.save(posteo);
    }

    @Override
    public Posteo update(Posteo posteo) {
        return this.save(posteo);
    }

    @Override
    public String deleteById(Long id) {
        posteRepo.deleteById(id);
        return "El posteo fue eliminado";
    }
}
