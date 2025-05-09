package com.cibertec.blogapp.repository;

import com.cibertec.blogapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por email (útil para login)
    Optional<Usuario> findByEmail(String email);

}

