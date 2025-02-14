package com.cibertec.blogapp.service;

import com.cibertec.blogapp.dto.ComentarioDTO;
import java.util.*;

public interface ComentarioService {
    ComentarioDTO crearComentario(ComentarioDTO comentarioDTO);
    List<ComentarioDTO> listarComentariosPorPublicacion(Long publicacionId);
    List<ComentarioDTO> listarComentariosPorUsuario(Long usuarioId); // Nuevo método
    List<ComentarioDTO> listarTodosLosComentarios(); // Nuevo método
    ComentarioDTO listarComentarioPorId(Long id); // Nuevo método
    void eliminarComentario(Long id);
}

