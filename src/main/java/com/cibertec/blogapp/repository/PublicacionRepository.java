package com.cibertec.blogapp.repository;

import com.cibertec.blogapp.model.Comentario;
import com.cibertec.blogapp.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

    // Listar todas las pubs de un usuario
    List<Publicacion> findByUsuarioId(Long usuarioId);
    
    List<Comentario> findByPublicacionId(Long publicacionId);
}




