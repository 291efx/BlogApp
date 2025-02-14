package com.cibertec.blogapp.service;

import com.cibertec.blogapp.model.Publicacion;

import java.util.List;

public interface PublicacionService {
    Publicacion guardar(Publicacion publicacion);
    Publicacion obtenerPorId(Long id);
    List<Publicacion> obtenerTodas();
    List<Publicacion> obtenerPorUsuario(Long usuarioId); // Nuevo método
    void eliminar(Long id);
}

