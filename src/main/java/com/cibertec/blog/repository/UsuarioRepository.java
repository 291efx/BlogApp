package com.cibertec.blog.repository;

import com.cibertec.blog.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
