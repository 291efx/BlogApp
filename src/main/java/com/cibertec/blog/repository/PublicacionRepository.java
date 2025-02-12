package com.cibertec.blog.repository;

import com.cibertec.blog.model.Publicacion;
import com.cibertec.blog.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    List<Publicacion> findByUsuario(Usuario usuario);
}

