package com.cibertec.blogapp.controller;

import com.cibertec.blogapp.model.Publicacion;
import com.cibertec.blogapp.service.PublicacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin
public class PublicacionController {

    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @GetMapping
    public ResponseEntity<List<Publicacion>> listarTodas() {
        return ResponseEntity.ok(publicacionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(publicacionService.obtenerPorId(id));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Publicacion>> listarPorUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(publicacionService.obtenerPorUsuario(id));
    }

    @PostMapping
    public ResponseEntity<Publicacion> crear(@RequestBody Publicacion publicacion) {
        return ResponseEntity.ok(publicacionService.crear(publicacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        publicacionService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
