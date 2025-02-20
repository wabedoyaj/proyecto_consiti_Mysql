package com.tutorial.crud.service;

import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.repository.RolRepository;
import com.tutorial.crud.security.entity.Rol;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByNombre(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);

    }
}
