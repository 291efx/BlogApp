package com.cibertec.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titulo", "Bienvenido a Mi Blog");
        return "index"; // Retorna la vista index.html
    }
}
