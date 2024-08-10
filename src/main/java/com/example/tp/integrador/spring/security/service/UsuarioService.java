package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.model.Rol;
import com.example.tp.integrador.spring.security.model.Usuario;
import com.example.tp.integrador.spring.security.repository.IUsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy
    private IRolService rolService;

    @Override
    public List<Usuario> getAll() {
        return usuarioRepo.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepo.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return this.save(usuario);
    }

    @Override
    public String deleteById(Long id) {
        usuarioRepo.deleteById(id);
        return "Usuario eliminado";
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    @Transactional
    public Usuario saveUserOauth(String email) {
        Usuario usuario = new Usuario();
        Set<Rol> roleList = new HashSet<>();
        Rol rolPorDefecto = rolService.findRolByName("USER");
        Rol managedRole = entityManager.merge(rolPorDefecto);

        roleList.add(managedRole);

        usuario.setEnable(true);
        usuario.setAccountNotExpired(true);
        usuario.setAccountNotLocked(true);
        usuario.setCredentialNotExpired(true);
        usuario.setUsername(email);
        usuario.setPassword(this.encriptPassword(this.generatedPassword()));
        usuario.setListaRoles(roleList);

        return this.save(usuario);
    }

    @Override
    public Optional<Usuario> findUserEntityByusername(String username) {
        return usuarioRepo.findUserEntityByusername(username);
    }

    public String generatedPassword(){
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
