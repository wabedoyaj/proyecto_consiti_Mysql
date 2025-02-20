package com.tutorial.crud.service;

import com.tutorial.crud.repository.UsuarioRepository;
import com.tutorial.crud.security.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existByNombreUsuario(nombreUsuario);
    }

    public boolean existByEmail(String email) {
        return usuarioRepository.existByEmail(email);
    }

    public  void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
