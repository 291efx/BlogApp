package com.cibertec.blogapp.service;

import org.springframework.web.multipart.MultipartFile;

import com.cibertec.blogapp.model.Publicacion;
import org.springframework.http.ResponseEntity;

public interface PublicacionService {

    ResponseEntity<?> crearPublicacion(String titulo, String contenido, MultipartFile archivo, Long usuarioId);
    ResponseEntity<?> listarTodas();
    ResponseEntity<?> obtenerPorId(Long id);
    ResponseEntity<?> eliminar(Long id);
    ResponseEntity<?> listarPorUsuario(Long usuarioId);
    ResponseEntity<?> actualizar(Long id, Publicacion publicacion);
}
