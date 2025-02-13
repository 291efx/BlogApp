package com.cibertec.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "index"; // Renderiza index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Renderiza login.html
    }

    @GetMapping("/publicacion/detalle")
    public String publicacionDetalle() {
        return "publicacion_detalle";
    }

    @GetMapping("/publicacion/nueva")
    public String publicacionForm() {
        return "publicacion_form";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }
}

