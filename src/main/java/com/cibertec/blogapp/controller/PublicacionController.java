package com.cibertec.blogapp.controller;

import com.cibertec.blogapp.model.Publicacion;
import com.cibertec.blogapp.service.impl.PublicacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class PublicacionController {

    @Autowired
    private PublicacionServiceImpl publicacionService;

    /** Crear publicación con archivo */
    @PostMapping("/crear")
    public ResponseEntity<?> crearConArchivo(
            @RequestParam String titulo,
            @RequestParam String contenido,
            @RequestParam MultipartFile archivo,
            @RequestParam Long usuarioId
    ) {
        return publicacionService.crearPublicacion(titulo, contenido, archivo, usuarioId);
    }

    /** Listar todas las publicaciones */
    @GetMapping
    public ResponseEntity<?> listarTodas() {
        return publicacionService.listarTodas();
    }

    /** Listar publicaciones de un usuario */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long usuarioId) {
        return publicacionService.listarPorUsuario(usuarioId);
    }

    /** Obtener publicación por ID */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return publicacionService.obtenerPorId(id);
    }

    /** Actualizar sólo título y contenido */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody Publicacion publicacion
    ) {
        return publicacionService.actualizar(id, publicacion);
    }

    /** Eliminar publicación */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return publicacionService.eliminar(id);
    }
    
    @PutMapping("/editar-con-archivo/{id}")
    public ResponseEntity<?> actualizarConArchivo(
            @PathVariable Long id,
            @RequestParam String titulo,
            @RequestParam String contenido,
            @RequestParam(required = false) MultipartFile archivo
    ) {
        return publicacionService.actualizarConArchivo(id, titulo, contenido, archivo);
    }
    

    /** Descargar archivo de una publicación 
    @GetMapping("/descargar/{filename:.+}")
    public ResponseEntity<?> descargarArchivo(@PathVariable String filename) {
        return publicacionService.descargarArchivo(filename);
    }
    */
}
