package com.cibertec.blogapp.service;

import com.cibertec.blogapp.dto.PublicacionDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PublicacionService {

    PublicacionDTO crearConArchivo(String titulo,
                                   String contenido,
                                   Long usuarioId,
                                   MultipartFile archivo);

    List<PublicacionDTO> listarTodas();

    Optional<PublicacionDTO> obtenerPorId(Long id);

    void eliminar(Long id);

    // ← NUEVOS
    List<PublicacionDTO> listarPorUsuario(Long usuarioId);

    PublicacionDTO actualizarPublicacion(Long id, PublicacionDTO datos);
}
