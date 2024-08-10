package com.example.tp.integrador.spring.security.repository;

import com.example.tp.integrador.spring.security.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {
    public Rol findRolBynombreRol(String roleName);
}
