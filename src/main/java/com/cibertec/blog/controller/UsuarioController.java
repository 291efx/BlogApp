package com.cibertec.blog.controller;

import com.cibertec.blog.model.Usuario;
import com.cibertec.blog.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @GetMapping("/email/{email}")
    public Optional<Usuario> obtenerUsuarioPorEmail(@PathVariable String email) {
        return usuarioService.obtenerUsuarioPorEmail(email);
    }

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
}
