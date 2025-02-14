package com.cibertec.blogapp.service;

import com.cibertec.blogapp.dto.ComentarioDTO;
import com.cibertec.blogapp.model.Comentario;
import com.cibertec.blogapp.model.Publicacion;
import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.repository.ComentarioRepository;
import com.cibertec.blogapp.repository.PublicacionRepository;
import com.cibertec.blogapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public ComentarioDTO crearComentario(ComentarioDTO comentarioDTO) {
        Usuario usuario = usuarioRepository.findById(comentarioDTO.getUsuarioId()).orElse(null);
        Publicacion publicacion = publicacionRepository.findById(comentarioDTO.getPublicacionId()).orElse(null);

        if (usuario == null || publicacion == null) {
            return null; // Manejo de error
        }

        Comentario comentario = new Comentario();
        comentario.setContenido(comentarioDTO.getContenido());
        comentario.setUsuario(usuario);
        comentario.setPublicacion(publicacion);

        comentario = comentarioRepository.save(comentario);
        return convertirADTO(comentario);
    }

    @Override
    public List<ComentarioDTO> listarComentariosPorPublicacion(Long publicacionId) {
        return comentarioRepository.findByPublicacionId(publicacionId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    private ComentarioDTO convertirADTO(Comentario comentario) {
        ComentarioDTO dto = new ComentarioDTO();
        dto.setId(comentario.getId());
        dto.setContenido(comentario.getContenido());
        dto.setFechaCreacion(comentario.getFechaCreacion());
        dto.setUsuarioId(comentario.getUsuario().getId());
        dto.setPublicacionId(comentario.getPublicacion().getId());
        return dto;
    }

    @Override
    public ComentarioDTO listarComentarioPorId(Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);
        return comentario.map(this::convertirADTO).orElse(null);
    }

    @Override
    public List<ComentarioDTO> listarComentariosPorUsuario(Long usuarioId) {
        return comentarioRepository.findByUsuarioId(usuarioId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComentarioDTO> listarTodosLosComentarios() {
        return comentarioRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
}
