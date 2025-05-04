package com.cibertec.blogapp.service;

import com.cibertec.blogapp.dto.UsuarioDTO;
import com.cibertec.blogapp.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> listarUsuarios();
    UsuarioDTO obtenerUsuarioPorId(Long id);
    Usuario registrarUsuario(Usuario usuario);
}


