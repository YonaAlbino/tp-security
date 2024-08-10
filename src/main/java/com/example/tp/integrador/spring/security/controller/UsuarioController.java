package com.example.tp.integrador.spring.security.controller;

import com.example.tp.integrador.spring.security.model.Rol;
import com.example.tp.integrador.spring.security.model.Usuario;
import com.example.tp.integrador.spring.security.service.IRolService;
import com.example.tp.integrador.spring.security.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usuario")
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        Optional<Rol> rolLeido;
        Set<Rol> listaRoles = new HashSet<>();

        for (Rol rol : usuario.getListaRoles()){
            rolLeido = rolService.findById(rol.getIdRol());

            if(rolLeido.isPresent())
                listaRoles.add(rolLeido.get());
        }
        usuario.setPassword(usuarioService.encriptPassword(usuario.getPassword()));
        usuario.setListaRoles(listaRoles);
        return ResponseEntity.ok(usuarioService.update(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return  ResponseEntity.ok(usuarioService.deleteById(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        Optional<Rol> rolLeido;
        Set<Rol> listaRoles = new HashSet<>();

        for (Rol rol : usuario.getListaRoles()){
            rolLeido = rolService.findById(rol.getIdRol());

            if(rolLeido.isPresent())
                listaRoles.add(rolLeido.get());
        }
        usuario.setPassword(usuarioService.encriptPassword(usuario.getPassword()));
        usuario.setListaRoles(listaRoles);
        return ResponseEntity.ok(usuarioService.save(usuario));
    }
}
