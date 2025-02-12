package com.cibertec.blog.service.impl;

import com.cibertec.blog.model.Comentario;
import com.cibertec.blog.model.Publicacion;
import com.cibertec.blog.repository.ComentarioRepository;
import com.cibertec.blog.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario agregarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public Optional<Comentario> obtenerComentarioPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    @Override
    public List<Comentario> listarComentariosPorPublicacion(Publicacion publicacion) {
        return comentarioRepository.findByPublicacion(publicacion);
    }
}
