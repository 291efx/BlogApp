package com.cibertec.blog.repository;

import com.cibertec.blog.model.Comentario;
import com.cibertec.blog.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByPublicacion(Publicacion publicacion);
}

