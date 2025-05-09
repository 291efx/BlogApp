package com.cibertec.blogapp.service.impl;

import com.cibertec.blogapp.model.Comentario;
import com.cibertec.blogapp.model.Publicacion;
import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.repository.ComentarioRepository;
import com.cibertec.blogapp.repository.PublicacionRepository;
import com.cibertec.blogapp.repository.UsuarioRepository;
import com.cibertec.blogapp.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PublicacionRepository publicacionRepo;

    @Override
    public ResponseEntity<?> crear(Comentario comentario) {
        Long usuarioId = comentario.getUsuario().getId();
        Long publicacionId = comentario.getPublicacion().getId();

        Optional<Usuario> usuarioOpt = usuarioRepo.findById(usuarioId);
        Optional<Publicacion> publicacionOpt = publicacionRepo.findById(publicacionId);

        if (usuarioOpt.isEmpty() || publicacionOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario o publicación no encontrada.");
        }

        comentario.setUsuario(usuarioOpt.get());
        comentario.setPublicacion(publicacionOpt.get());
        comentario.setFechaCreacion(LocalDateTime.now());

        Comentario guardado = comentarioRepo.save(comentario);
        return ResponseEntity.status(201).body(guardado);
    }

    @Override
    public ResponseEntity<?> listarTodos() {
        List<Comentario> comentarios = comentarioRepo.findAll();
        return comentarios.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(comentarios);
    }

    @Override
    public ResponseEntity<?> obtenerPorId(Long id) {
        Optional<Comentario> comentarioOpt = comentarioRepo.findById(id);

        if (comentarioOpt.isPresent()) {
            // Si se encuentra el comentario, devolvemos 200 OK con el comentario
            return ResponseEntity.ok(comentarioOpt.get());
        } else {
            // Si no se encuentra, devolvemos 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    public ResponseEntity<?> listarPorPublicacion(Long publicacionId) {
        if (!publicacionRepo.existsById(publicacionId)) {
            return ResponseEntity.badRequest().body("La publicación no existe.");
        }

        List<Comentario> comentarios = comentarioRepo.findByPublicacionId(publicacionId);
        return comentarios.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(comentarios);
    }

    @Override
    public ResponseEntity<?> listarPorUsuario(Long usuarioId) {
        if (!usuarioRepo.existsById(usuarioId)) {
            return ResponseEntity.badRequest().body("El usuario no existe.");
        }

        List<Comentario> comentarios = comentarioRepo.findByUsuarioId(usuarioId);
        return comentarios.isEmpty()?//es vacio? devuelve:
        		ResponseEntity.noContent().build() : //caso contrario (no es vacio)
        			ResponseEntity.ok(comentarios);
    }

    @Override
    public ResponseEntity<?> actualizar(Long id, Comentario comentarioActualizado) {
        Optional<Comentario> comentarioExistente = comentarioRepo.findById(id);
        if (comentarioExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Comentario original = comentarioExistente.get();
        original.setContenido(comentarioActualizado.getContenido());

        Comentario actualizado = comentarioRepo.save(original);
        return ResponseEntity.ok(actualizado);
    }

    @Override
    public ResponseEntity<?> eliminar(Long id) {
        if (!comentarioRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        comentarioRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}