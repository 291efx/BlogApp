package com.cibertec.blog.service;

import com.cibertec.blog.model.Usuario;

import java.util.*;

public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);
    Optional<Usuario> obtenerUsuarioPorId(Long id);
    Optional<Usuario> obtenerUsuarioPorEmail(String email);
    List<Usuario> listarUsuarios();
}

