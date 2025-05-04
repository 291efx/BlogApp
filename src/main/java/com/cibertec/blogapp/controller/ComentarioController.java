package com.cibertec.blogapp.controller;

import com.cibertec.blogapp.dto.ComentarioDTO;
import com.cibertec.blogapp.service.ComentarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    // Obtener comentarios de una publicación
    @GetMapping("/publicacion/{publicacionId}")
    public ResponseEntity<List<ComentarioDTO>> listarPorPublicacion(@PathVariable Long publicacionId) {
        return ResponseEntity.ok(comentarioService.listarPorPublicacion(publicacionId));
    }

    // Crear nuevo comentario
    @PostMapping
    public ResponseEntity<ComentarioDTO> crear(@RequestBody ComentarioDTO dto) {
        return ResponseEntity.ok(comentarioService.crearComentario(dto));
    }
}
