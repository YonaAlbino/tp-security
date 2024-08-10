package com.example.tp.integrador.spring.security.controller;

import com.example.tp.integrador.spring.security.model.Permiso;
import com.example.tp.integrador.spring.security.model.Posteo;
import com.example.tp.integrador.spring.security.service.IPosteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posteo")
@PreAuthorize("hasRole('ADMIN')")
public class PosteoController {

    @Autowired
    private IPosteoService posteoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('AUTHOR','ADMIN','USER')")
    public ResponseEntity<List<Posteo>> getAll(){
        return ResponseEntity.ok(posteoService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('AUTHOR','ADMIN','USER')")
    public ResponseEntity<Posteo> findById(@PathVariable Long id){
        Optional<Posteo> posteo = posteoService.findById(id);
        return posteo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('AUTHOR','ADMIN')")
    public ResponseEntity<Posteo> update(@RequestBody Posteo posteo){
        return ResponseEntity.ok(posteoService.update(posteo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return  ResponseEntity.ok(posteoService.deleteById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('AUTHOR','ADMIN')")
    public ResponseEntity<Posteo> save(@RequestBody Posteo posteo){
        return ResponseEntity.ok(posteoService.save(posteo));
    }
}
