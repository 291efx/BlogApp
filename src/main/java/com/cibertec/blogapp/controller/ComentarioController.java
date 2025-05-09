package com.cibertec.blogapp.controller;

import com.cibertec.blogapp.model.Comentario;
import com.cibertec.blogapp.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin(origins = "http://localhost:4200")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Comentario comentario) {
        return comentarioService.crear(comentario);
    }

    @GetMapping    
    public ResponseEntity<?> listarTodos() {
        return comentarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return comentarioService.obtenerPorId(id);
    }

    @GetMapping("/publicacion/{publicacionId}")
    public ResponseEntity<?> listarPorPublicacion(@PathVariable Long publicacionId) {
        return comentarioService.listarPorPublicacion(publicacionId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long usuarioId) {
        return comentarioService.listarPorUsuario(usuarioId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Comentario comentario) {
        return comentarioService.actualizar(id, comentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return comentarioService.eliminar(id);
    }
}

