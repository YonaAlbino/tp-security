package com.example.tp.integrador.spring.security.controller;

import com.example.tp.integrador.spring.security.model.Permiso;
import com.example.tp.integrador.spring.security.model.Rol;
import com.example.tp.integrador.spring.security.repository.IPermisoRepository;
import com.example.tp.integrador.spring.security.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/rol")
@PreAuthorize("hasRole('ADMIN')")
public class RolController {

    @Autowired
    private IRolService rolService;

    @Autowired
    private IPermisoRepository permisoRepo;

    @GetMapping
    public ResponseEntity<List<Rol>> getAll(){
        return ResponseEntity.ok(rolService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> findById(@PathVariable Long id){
        Optional<Rol> rol = rolService.findById(id);
        return rol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Rol> update(@RequestBody Rol rol){
        Optional<Permiso> permisoLeido;
        Set<Permiso> listaPermiso = new HashSet<>();

        for (Permiso permiso : rol.getListaPermiso()){
            permisoLeido = permisoRepo.findById(permiso.getIdPermiso());

            if(permisoLeido.isPresent())
                listaPermiso.add((permisoLeido.get()));
        }

        rol.setListaPermiso(listaPermiso);
        return ResponseEntity.ok(rolService.update(rol));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(rolService.deleteById(id));
    }

    @PostMapping
    public ResponseEntity<Rol> save(@RequestBody Rol rol){
        Optional<Permiso> permisoLeido;
        Set<Permiso> listaPermiso = new HashSet<>();

        for (Permiso permiso : rol.getListaPermiso()){
            permisoLeido = permisoRepo.findById(permiso.getIdPermiso());

            if(permisoLeido.isPresent())
                listaPermiso.add((permisoLeido.get()));
        }

        rol.setListaPermiso(listaPermiso);
        return ResponseEntity.ok(rolService.save(rol));
    }

}
