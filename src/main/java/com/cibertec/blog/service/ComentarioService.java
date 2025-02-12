package com.cibertec.blog.service;

import com.cibertec.blog.model.Comentario;
import com.cibertec.blog.model.Publicacion;

import java.util.*;

public interface ComentarioService {
    Comentario agregarComentario(Comentario comentario);
    Optional<Comentario> obtenerComentarioPorId(Long id);
    List<Comentario> listarComentariosPorPublicacion(Publicacion publicacion);
}

