package com.cibertec.blogapp.repository;

import com.cibertec.blogapp.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

    @Query("SELECT p.nombreArchivo FROM Publicacion p WHERE p.usuario.id = :usuarioId")
    List<String> findNombresArchivosByUsuarioId(@Param("usuarioId") Long usuarioId);
}



