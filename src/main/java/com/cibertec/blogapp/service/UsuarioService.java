package com.cibertec.blogapp.service;

import com.cibertec.blogapp.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO obtenerPorId(Long id);

    UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO);

    void eliminarUsuario(Long id);
}
