package com.cibertec.blogapp.service;

import com.cibertec.blogapp.model.Publicacion;
import com.cibertec.blogapp.repository.PublicacionRepository;
import com.cibertec.blogapp.service.PublicacionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    private final PublicacionRepository publicacionRepository;

    public PublicacionServiceImpl(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    @Override
    public Publicacion guardar(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    @Override
    public Publicacion obtenerPorId(Long id) {
        Optional<Publicacion> publicacion = publicacionRepository.findById(id);
        return publicacion.orElse(null);
    }

    @Override
    public List<Publicacion> obtenerTodas() {
        return publicacionRepository.findAll();
    }
    @Override
    public List<Publicacion> obtenerPorUsuario(Long usuarioId) {
        return publicacionRepository.findByUsuarioId(usuarioId); // Usamos el método del repositorio
    }

    @Override
    public void eliminar(Long id) {
        publicacionRepository.deleteById(id);
    }
}

