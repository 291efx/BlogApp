package com.cibertec.blogapp.service;

import com.cibertec.blogapp.model.Publicacion;

import java.util.List;

public interface PublicacionService {
    List<Publicacion> obtenerTodas();
    Publicacion obtenerPorId(Long id);
    List<Publicacion> obtenerPorUsuario(Long usuarioId);
    Publicacion crear(Publicacion publicacion);
    void eliminar(Long id);
}
