package com.tutorial.crud.repository;

import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByNombre(RolNombre rolNombre);
}
