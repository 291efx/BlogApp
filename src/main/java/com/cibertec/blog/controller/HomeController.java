package com.cibertec.blog.controller;

import com.cibertec.blog.model.Publicacion;
import com.cibertec.blog.service.PublicacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final PublicacionService publicacionService;

    public HomeController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    // Página de inicio con lista de publicaciones
    @GetMapping
    public String home(Model model) {
        List<Publicacion> publicaciones = publicacionService.listarPublicaciones();
        model.addAttribute("publicaciones", publicaciones);
        return "index"; // Redirige a index.html
    }

    // Ver detalles de una publicación específica
    @GetMapping("/publicacion/{id}")
    public String verPublicacion(@PathVariable Long id, Model model) {
        Optional<Publicacion> publicacionOptional = publicacionService.obtenerPublicacionPorId(id);

        if (publicacionOptional.isPresent()) {
            model.addAttribute("publicacion", publicacionOptional.get());
            return "publicacion_detalle"; // Carga publicacion_detalle.html
        } else {
            return "error"; // Muestra error.html si la publicación no existe
        }
    }

    // Página de inicio de sesión
    @GetMapping("/login")
    public String login() {
        return "login"; // Redirige a login.html
    }

    // Página de registro de usuario
    @GetMapping("/registro")
    public String registro() {
        return "registro"; // Redirige a registro.html
    }
}
