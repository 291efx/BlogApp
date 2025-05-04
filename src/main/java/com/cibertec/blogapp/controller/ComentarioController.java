package com.cibertec.blogapp.controller;

import com.cibertec.blogapp.dto.ComentarioDTO;
import com.cibertec.blogapp.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    // Crear comentario
    @PostMapping("/crear")
    public ResponseEntity<ComentarioDTO> crear(@RequestBody ComentarioDTO dto) {
        return ResponseEntity.ok(comentarioService.crear(dto));
    }

    // Listar todos los comentarios
    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> listarTodos() {
        return ResponseEntity.ok(comentarioService.listarTodos());
    }

    // Obtener comentario por ID
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> obtenerPorId(@PathVariable Long id) {
        return comentarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar comentarios por publicación
    @GetMapping("/publicacion/{publicacionId}")
    public ResponseEntity<List<ComentarioDTO>> listarPorPublicacion(@PathVariable Long publicacionId) {
        return ResponseEntity.ok(comentarioService.listarPorPublicacion(publicacionId));
    }

    // Listar comentarios por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ComentarioDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comentarioService.listarPorUsuario(usuarioId));
    }

    // Actualizar comentario por ID
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> actualizar(
            @PathVariable Long id,
            @RequestBody ComentarioDTO dto
    ) {
        return ResponseEntity.ok(comentarioService.actualizar(id, dto));
    }

    // Eliminar comentario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        comentarioService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
