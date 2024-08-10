package com.example.tp.integrador.spring.security.controller;

import com.example.tp.integrador.spring.security.model.Autor;
import com.example.tp.integrador.spring.security.model.Posteo;
import com.example.tp.integrador.spring.security.model.Usuario;
import com.example.tp.integrador.spring.security.service.IAutorService;
import com.example.tp.integrador.spring.security.service.IPosteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
@PreAuthorize("hasRole('ADMIN')")
public class AutorController {

    @Autowired
    private IAutorService autorService;

    @Autowired
    private IPosteoService posteoService;

    @PreAuthorize("hasAnyRole('AUTHOR','ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<Autor>> getAll(){
        return ResponseEntity.ok(autorService.getAll());
    }

    @PreAuthorize("hasAnyRole('AUTHOR','ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Autor> findById(@PathVariable Long id){
        Optional<Autor> autor = autorService.findById(id);
        return autor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Autor> update(@RequestBody Autor autor){
        Optional<Posteo> posteoLeido;
        List<Posteo> listaPosteo = new ArrayList<>();

        for (Posteo posteo : autor.getListaPosteos()){
            posteoLeido = posteoService.findById(posteo.getIdPosteo());

            if (posteoLeido.isPresent())
                listaPosteo.add(posteoLeido.get());
        }
        autor.setListaPosteos(listaPosteo);
        return ResponseEntity.ok(autorService.update(autor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return  ResponseEntity.ok(autorService.deleteById(id));
    }

    @PostMapping
    public ResponseEntity<Autor> save(@RequestBody Autor autor){
        Optional<Posteo> posteoLeido;
        List<Posteo> listaPosteo = new ArrayList<>();

        for (Posteo posteo : autor.getListaPosteos()){
            posteoLeido = posteoService.findById(posteo.getIdPosteo());

            if (posteoLeido.isPresent())
                listaPosteo.add(posteoLeido.get());
        }
        autor.setListaPosteos(listaPosteo);
        return ResponseEntity.ok(autorService.save(autor));
    }

}
