package com.cibertec.blogapp.repository;

import com.cibertec.blogapp.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByPublicacionId(Long publicacionId);
    List<Comentario> findByUsuarioId(Long usuarioId);
    
}


