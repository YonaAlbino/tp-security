package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> getAll();
    public Optional<Usuario> findById(Long id);
    public Usuario save(Usuario usuario);
    public Usuario update(Usuario usuario);
    public String deleteById(Long id);
    public String encriptPassword(String password);
    public Usuario saveUserOauth(String email);
    public  Optional<Usuario> findUserEntityByusername(String username);
}
