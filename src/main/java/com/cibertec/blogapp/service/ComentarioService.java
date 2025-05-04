package com.cibertec.blogapp.service;

import com.cibertec.blogapp.dto.ComentarioDTO;

import java.util.List;

public interface ComentarioService {
    List<ComentarioDTO> listarPorPublicacion(Long publicacionId);
    ComentarioDTO crearComentario(ComentarioDTO comentarioDTO);
}


