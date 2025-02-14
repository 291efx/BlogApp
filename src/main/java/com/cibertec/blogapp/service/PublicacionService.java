package com.cibertec.blogapp.service;

import com.cibertec.blogapp.model.Publicacion;

import java.util.List;

public interface PublicacionService {
    Publicacion guardar(Publicacion publicacion);
    Publicacion obtenerPorId(Long id);
    List<Publicacion> obtenerTodas();
    void eliminar(Long id);
}

