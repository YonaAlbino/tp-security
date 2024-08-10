package com.example.tp.integrador.spring.security.controller;

import com.example.tp.integrador.spring.security.model.Permiso;
import com.example.tp.integrador.spring.security.model.Usuario;
import com.example.tp.integrador.spring.security.service.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permiso")
@PreAuthorize("hasRole('ADMIN')")
public class PermisoController {

    @Autowired
    private IPermisoService permisoService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Permiso>> getAll(){
        return ResponseEntity.ok(permisoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permiso> findById(@PathVariable Long id){
        Optional<Permiso> permiso = permisoService.findById(id);
        return permiso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Permiso> update(@RequestBody Permiso permiso){
        return ResponseEntity.ok(permisoService.update(permiso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return  ResponseEntity.ok(permisoService.deleteById(id));
    }

    @PostMapping
    public ResponseEntity<Permiso> save(@RequestBody Permiso permiso){
        return ResponseEntity.ok(permisoService.save(permiso));
    }
}
