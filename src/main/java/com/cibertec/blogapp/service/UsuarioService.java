package com.cibertec.blogapp.service;

import com.cibertec.blogapp.model.Usuario;
import org.springframework.http.ResponseEntity;


public interface UsuarioService {
    ResponseEntity<?> registrar(Usuario usuario);
    ResponseEntity<?> listar();
    ResponseEntity<?> obtenerPorId(Long id);
    ResponseEntity<?> actualizar(Long id, Usuario usuario);
    ResponseEntity<?> eliminar(Long id);
}
