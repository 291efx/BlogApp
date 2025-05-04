package com.cibertec.blogapp.service;

import com.cibertec.blogapp.dto.ComentarioDTO;

import java.util.List;
import java.util.Optional;

public interface ComentarioService {
    ComentarioDTO crear(ComentarioDTO comentarioDTO);
    List<ComentarioDTO> listarTodos();
    Optional<ComentarioDTO> obtenerPorId(Long id);
    List<ComentarioDTO> listarPorPublicacion(Long publicacionId);
    List<ComentarioDTO> listarPorUsuario(Long usuarioId);
    ComentarioDTO actualizar(Long id, ComentarioDTO comentarioDTO);
    void eliminar(Long id);
}