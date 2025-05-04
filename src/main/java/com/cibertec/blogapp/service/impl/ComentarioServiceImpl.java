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

import java.util.List;
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
    public List<ComentarioDTO> listarPorPublicacion(Long publicacionId) {
        return comentarioRepository.findByPublicacionId(publicacionId).stream().map(comentario -> {
            ComentarioDTO dto = new ComentarioDTO();
            dto.setId(comentario.getId());
            dto.setContenido(comentario.getContenido());
            dto.setUsuarioId(comentario.getUsuario().getId());
            dto.setPublicacionId(comentario.getPublicacion().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO crearComentario(ComentarioDTO comentarioDTO) {
        Long usuarioId = comentarioDTO.getUsuarioId() != null ? comentarioDTO.getUsuarioId().longValue() : null;
        Long publicacionId = comentarioDTO.getPublicacionId() != null ? comentarioDTO.getPublicacionId().longValue() : null;

        Usuario usuario = usuarioId != null ? usuarioRepository.findById(usuarioId).orElse(null) : null;
        Publicacion publicacion = publicacionId != null ? publicacionRepository.findById(publicacionId).orElse(null) : null;

        if (usuario == null || publicacion == null) return null;

        Comentario comentario = new Comentario();
        comentario.setContenido(comentarioDTO.getContenido());
        comentario.setUsuario(usuario);
        comentario.setPublicacion(publicacion);

        Comentario nuevo = comentarioRepository.save(comentario);

        ComentarioDTO dto = new ComentarioDTO();
        dto.setId(nuevo.getId());
        dto.setContenido(nuevo.getContenido());
        dto.setUsuarioId(usuario.getId());
        dto.setPublicacionId(publicacion.getId());

        return dto;
    }
}

