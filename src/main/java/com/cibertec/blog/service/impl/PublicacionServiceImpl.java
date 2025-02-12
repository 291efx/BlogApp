package com.cibertec.blog.service.impl;

import com.cibertec.blog.model.Publicacion;
import com.cibertec.blog.model.Usuario;
import com.cibertec.blog.repository.PublicacionRepository;
import com.cibertec.blog.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public Publicacion crearPublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    @Override
    public Optional<Publicacion> obtenerPublicacionPorId(Long id) {
        return publicacionRepository.findById(id);
    }

    @Override
    public List<Publicacion> listarPublicaciones() {
        return publicacionRepository.findAll();
    }

    @Override
    public List<Publicacion> listarPublicacionesPorUsuario(Usuario usuario) {
        return publicacionRepository.findByUsuario(usuario);
    }
}
