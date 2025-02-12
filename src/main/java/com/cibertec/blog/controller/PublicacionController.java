package com.cibertec.blog.controller;

import com.cibertec.blog.model.Publicacion;
import com.cibertec.blog.model.Usuario;
import com.cibertec.blog.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @PostMapping("/crear")
    public Publicacion crearPublicacion(@RequestBody Publicacion publicacion) {
        return publicacionService.crearPublicacion(publicacion);
    }

    @GetMapping("/{id}")
    public Optional<Publicacion> obtenerPublicacionPorId(@PathVariable Long id) {
        return publicacionService.obtenerPublicacionPorId(id);
    }

    @GetMapping("/listar")
    public List<Publicacion> listarPublicaciones() {
        return publicacionService.listarPublicaciones();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Publicacion> listarPublicacionesPorUsuario(@PathVariable Long idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        return publicacionService.listarPublicacionesPorUsuario(usuario);
    }
}
