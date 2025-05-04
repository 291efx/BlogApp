package com.cibertec.blogapp.repository;

import com.cibertec.blogapp.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

    // Buscar publicaciones de un usuario específico
    List<Publicacion> findByUsuarioId(Long usuarioId);
}





