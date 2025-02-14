package com.cibertec.blog.controller;

import ch.qos.logback.core.model.Model;
import com.cibertec.blog.model.Publicacion;
import com.cibertec.blog.model.Usuario;
import com.cibertec.blog.service.PublicacionService;
import com.cibertec.blog.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;
    private UsuarioService usuarioService;


    @PostMapping("/publicaciones")
    public ResponseEntity<?> guardarPublicacion(@RequestBody Publicacion publicacion) {
        try {
            Publicacion nuevaPublicacion = publicacionService.crearPublicacion(publicacion);
            return ResponseEntity.ok(nuevaPublicacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar publicación");
        }
    }


    @GetMapping("/form")
    public String mostrarFormularioPublicacion(Model model) {
        return "publicacion_form"; // Asegúrate de que este nombre coincide con el HTML
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
