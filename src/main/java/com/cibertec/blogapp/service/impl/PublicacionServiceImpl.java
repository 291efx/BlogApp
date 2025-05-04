package com.cibertec.blogapp.service.impl;

import com.cibertec.blogapp.model.Publicacion;
import com.cibertec.blogapp.repository.PublicacionRepository;
import com.cibertec.blogapp.service.PublicacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    private final PublicacionRepository publicacionRepository;

    public PublicacionServiceImpl(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    @Override
    public List<Publicacion> obtenerTodas() {
        return publicacionRepository.findAll();
    }

    @Override
    public Publicacion obtenerPorId(Long id) {
        return publicacionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Publicacion> obtenerPorUsuario(Long usuarioId) {
        return publicacionRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public Publicacion crear(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    @Override
    public void eliminar(Long id) {
        publicacionRepository.deleteById(id);
    }
}



