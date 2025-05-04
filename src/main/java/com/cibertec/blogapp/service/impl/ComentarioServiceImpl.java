package com.cibertec.blogapp.service.impl;

import com.cibertec.blogapp.dto.ComentarioDTO;
import com.cibertec.blogapp.model.Comentario;
import com.cibertec.blogapp.model.Publicacion;
import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.repository.ComentarioRepository;
import com.cibertec.blogapp.repository.PublicacionRepository;
import com.cibertec.blogapp.repository.UsuarioRepository;
import com.cibertec.blogapp.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public ComentarioDTO crear(ComentarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Publicacion publicacion = publicacionRepository.findById(dto.getPublicacionId())
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        Comentario comentario = new Comentario();
        comentario.setContenido(dto.getContenido());
        comentario.setFechaCreacion(LocalDateTime.now());
        comentario.setUsuario(usuario);
        comentario.setPublicacion(publicacion);
        Comentario guardado = comentarioRepository.save(comentario);

        return mapToDTO(guardado);
    }

    @Override
    public List<ComentarioDTO> listarTodos() {
        return comentarioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ComentarioDTO> obtenerPorId(Long id) {
        return comentarioRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Override
    public List<ComentarioDTO> listarPorPublicacion(Long publicacionId) {
        return comentarioRepository.findByPublicacionId(publicacionId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComentarioDTO> listarPorUsuario(Long usuarioId) {
        return comentarioRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO actualizar(Long id, ComentarioDTO dto) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        comentario.setContenido(dto.getContenido());
        // No cambiamos usuario o publicación
        Comentario modificado = comentarioRepository.save(comentario);
        return mapToDTO(modificado);
    }

    @Override
    public void eliminar(Long id) {
        comentarioRepository.deleteById(id);
    }

    private ComentarioDTO mapToDTO(Comentario c) {
        ComentarioDTO dto = new ComentarioDTO();
        dto.setId(c.getId());
        dto.setContenido(c.getContenido());
        dto.setFechaCreacion(c.getFechaCreacion());
        dto.setUsuarioId(c.getUsuario().getId());
        dto.setPublicacionId(c.getPublicacion().getId());
        return dto;
    }
}