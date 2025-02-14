package com.cibertec.blogapp.service;

import com.cibertec.blogapp.dto.UsuarioDTO;
import com.cibertec.blogapp.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> listarUsuarios();
    Usuario registrarUsuario(Usuario usuario);
    UsuarioDTO obtenerPorId(Long id); // Cambiar de Usuario a UsuarioDTO
    Usuario obtenerEntidadPorId(Long id); // <-- Nuevo método
}

