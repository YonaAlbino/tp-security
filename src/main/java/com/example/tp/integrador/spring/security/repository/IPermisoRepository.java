package com.example.tp.integrador.spring.security.repository;

import com.example.tp.integrador.spring.security.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermisoRepository extends JpaRepository<Permiso, Long> {
}
