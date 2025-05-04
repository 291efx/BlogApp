package com.cibertec.blogapp.repository;

import com.cibertec.blogapp.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    // Obtener todos los comentarios de una publicación
    List<Comentario> findByPublicacionId(Long publicacionId);
}

