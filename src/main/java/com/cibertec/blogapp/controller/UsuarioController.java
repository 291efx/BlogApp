package com.cibertec.blogapp.controller;

import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /** Registra un nuevo usuario */
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrar(usuario); 
    }

    /** Lista todos los usuarios */
    @GetMapping
    public ResponseEntity<?> listar() {
        return usuarioService.listar();  
    }

    /** Obtiene un usuario por ID */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id); 
    }

    /** Actualiza un usuario existente */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizar(id, usuario);  
    }

    /** Elimina un usuario por ID */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return usuarioService.eliminar(id);  
    }
}
