package com.cibertec.blogapp.service;

import com.cibertec.blogapp.model.Comentario;
import org.springframework.http.ResponseEntity;

public interface ComentarioService {
    ResponseEntity<?> crear(Comentario comentario);
    ResponseEntity<?> listarTodos();
    ResponseEntity<?> obtenerPorId(Long id);
    ResponseEntity<?> listarPorPublicacion(Long publicacionId);
    ResponseEntity<?> listarPorUsuario(Long usuarioId);
    ResponseEntity<?> actualizar(Long id, Comentario comentario);
    ResponseEntity<?> eliminar(Long id);
}
