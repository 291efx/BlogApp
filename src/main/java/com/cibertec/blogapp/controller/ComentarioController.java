package com.cibertec.blogapp.controller;

import com.cibertec.blogapp.dto.ComentarioDTO;
import com.cibertec.blogapp.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    // ✅ Agregar comentario a una publicación
    @PostMapping("/crear")
    public ResponseEntity<ComentarioDTO> crearComentario(@RequestBody ComentarioDTO comentarioDTO) {
        ComentarioDTO nuevoComentario = comentarioService.crearComentario(comentarioDTO);
        return nuevoComentario != null ? ResponseEntity.ok(nuevoComentario) : ResponseEntity.badRequest().build();
    }

    // ✅ Listar comentarios por publicación
    @GetMapping("/publicacion/{publicacionId}")
    public ResponseEntity<List<ComentarioDTO>> listarComentarios(@PathVariable Long publicacionId) {
        return ResponseEntity.ok(comentarioService.listarComentariosPorPublicacion(publicacionId));
    }

    // ✅ Eliminar comentario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Obtener un comentario por ID
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(@PathVariable Long id) {
        ComentarioDTO comentarioDTO = comentarioService.listarComentarioPorId(id);
        return comentarioDTO != null ? ResponseEntity.ok(comentarioDTO) : ResponseEntity.notFound().build();
    }

    // ✅ Obtener todos los comentarios de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ComentarioDTO>> listarComentariosPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comentarioService.listarComentariosPorUsuario(usuarioId));
    }

    // ✅ Obtener todos los comentarios
    @GetMapping("/todos")
    public ResponseEntity<List<ComentarioDTO>> listarTodosLosComentarios() {
        return ResponseEntity.ok(comentarioService.listarTodosLosComentarios());
    }

}
