package com.cibertec.blog.controller;

import com.cibertec.blog.model.Comentario;
import com.cibertec.blog.model.Publicacion;
import com.cibertec.blog.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/agregar")
    public String agregarComentario(@ModelAttribute Comentario comentario) {
        comentarioService.agregarComentario(comentario);
        return "redirect:/publicaciones/" + comentario.getPublicacion().getId();
    }

    @GetMapping("/{id}")
    public String obtenerComentarioPorId(@PathVariable Long id, Model model) {
        Optional<Comentario> comentario = comentarioService.obtenerComentarioPorId(id);
        comentario.ifPresent(c -> model.addAttribute("comentario", c));
        return comentario.isPresent() ? "comentario-detalle" : "error";
    }

    @GetMapping("/publicacion/{idPublicacion}")
    public String listarComentariosPorPublicacion(@PathVariable Long idPublicacion, Model model) {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(idPublicacion);
        List<Comentario> comentarios = comentarioService.listarComentariosPorPublicacion(publicacion);
        model.addAttribute("comentarios", comentarios);
        return "comentarios-lista";
    }
}

