package com.cibertec.blogapp.controller;

import com.cibertec.blogapp.dto.PublicacionDTO;
import com.cibertec.blogapp.model.Publicacion;
import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.service.PublicacionService;
import com.cibertec.blogapp.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    private final PublicacionService publicacionService;
    private final UsuarioService usuarioService;

    public PublicacionController(PublicacionService publicacionService, UsuarioService usuarioService) {
        this.publicacionService = publicacionService;
        this.usuarioService = usuarioService;
    }

    // ✅ Registrar publicación con archivo
    @PostMapping("/crear")  // Agrega una ruta específica
    public ResponseEntity<PublicacionDTO> crearPublicacion(
            @RequestParam("titulo") String titulo,
            @RequestParam("contenido") String contenido,
            @RequestParam("usuarioId") Long usuarioId,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo) throws IOException {

        Usuario usuario = usuarioService.obtenerEntidadPorId(usuarioId);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(titulo);
        publicacion.setContenido(contenido);
        publicacion.setUsuario(usuario);

        if (archivo != null && !archivo.isEmpty()) {
            publicacion.setArchivo(archivo.getBytes());
            publicacion.setNombreArchivo(archivo.getOriginalFilename());
            publicacion.setTipoArchivo(archivo.getContentType());
        }

        publicacion = publicacionService.guardar(publicacion);

        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(publicacion));
    }

    // ✅ Obtener todas las publicaciones
    @GetMapping
    public ResponseEntity<List<PublicacionDTO>> obtenerPublicaciones() {
        List<Publicacion> publicaciones = publicacionService.obtenerTodas();
        List<PublicacionDTO> publicacionesDTO = publicaciones.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(publicacionesDTO);
    }

    // ✅ Obtener una publicación por ID
    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacion(@PathVariable Long id) {
        Publicacion publicacion = publicacionService.obtenerPorId(id);
        if (publicacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(convertirADTO(publicacion));
    }

    // ✅ Descargar archivo de una publicación
    @GetMapping("/{id}/archivo")
    public ResponseEntity<byte[]> descargarArchivo(@PathVariable Long id) {
        Publicacion publicacion = publicacionService.obtenerPorId(id);
        if (publicacion == null || publicacion.getArchivo() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + publicacion.getNombreArchivo())
                .header("Content-Type", publicacion.getTipoArchivo())
                .body(publicacion.getArchivo());
    }

    // ✅ Eliminar publicación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long id) {
        publicacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Método para convertir a DTO
    private PublicacionDTO convertirADTO(Publicacion publicacion) {
        PublicacionDTO dto = new PublicacionDTO();
        dto.setId(publicacion.getId());
        dto.setTitulo(publicacion.getTitulo());
        dto.setContenido(publicacion.getContenido());
        dto.setNombreArchivo(publicacion.getNombreArchivo());
        dto.setTipoArchivo(publicacion.getTipoArchivo());
        dto.setFechaPublicacion(publicacion.getFechaPublicacion());
        dto.setUsuarioId(publicacion.getUsuario().getId());
        return dto;
    }
}
