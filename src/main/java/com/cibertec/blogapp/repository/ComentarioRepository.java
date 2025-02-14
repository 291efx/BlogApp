package com.cibertec.blogapp.repository;

import com.cibertec.blogapp.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByPublicacionId(Long publicacionId);
    List<Comentario> findByUsuarioId(Long usuarioId); // Nuevo método


    @Query("SELECT c.contenido FROM Comentario c WHERE c.usuario.id = :usuarioId")
    List<String> findComentariosByUsuarioId(@Param("usuarioId") Long usuarioId);


}
