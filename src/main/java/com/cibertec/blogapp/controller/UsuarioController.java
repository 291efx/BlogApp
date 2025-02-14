package com.cibertec.blogapp.controller;

import com.cibertec.blogapp.dto.UsuarioDTO;
import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerEntidadPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(usuario));
    }
}
