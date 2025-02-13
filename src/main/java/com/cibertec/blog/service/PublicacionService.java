package com.cibertec.blog.service;

import com.cibertec.blog.model.Publicacion;
import com.cibertec.blog.model.Usuario;

import java.util.*;

public interface PublicacionService {
    Publicacion crearPublicacion(Publicacion publicacion);
    Optional<Publicacion> obtenerPublicacionPorId(Long id);
    List<Publicacion> listarPublicaciones();
    List<Publicacion> listarPublicacionesPorUsuario(Usuario usuario);

}
