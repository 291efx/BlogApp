package com.cibertec.blogapp.controller;

import com.cibertec.blogapp.dto.PublicacionDTO;
import com.cibertec.blogapp.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin
public class PublicacionController {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private PublicacionService publicacionService;

    /** Crear publicación con archivo */
    @PostMapping("/crear-con-archivo")
    public ResponseEntity<PublicacionDTO> crearConArchivo(
            @RequestParam String titulo,
            @RequestParam String contenido,
            @RequestParam Long usuarioId,
            @RequestParam MultipartFile archivo
    ) {
        PublicacionDTO dto = publicacionService.crearConArchivo(titulo, contenido, usuarioId, archivo);
        return ResponseEntity.ok(dto);
    }

    /** Listar todas las publicaciones */
    @GetMapping
    public ResponseEntity<List<PublicacionDTO>> listarTodas() {
        return ResponseEntity.ok(publicacionService.listarTodas());
    }

    /** Listar publicaciones de un usuario */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PublicacionDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(publicacionService.listarPorUsuario(usuarioId));
    }

    /** Obtener publicación por ID */
    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPorId(@PathVariable Long id) {
        return publicacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Actualizar sólo título y contenido */
    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizar(
            @PathVariable Long id,
            @RequestBody PublicacionDTO datos
    ) {
        PublicacionDTO actualizado = publicacionService.actualizarPublicacion(id, datos);
        return ResponseEntity.ok(actualizado);
    }

    /** Eliminar publicación */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        publicacionService.eliminar(id);
        return ResponseEntity.ok().build();
    }

    /** Descargar archivo de una publicación */
    @GetMapping("/descargar/{filename:.+}")
    public ResponseEntity<byte[]> descargarArchivo(@PathVariable String filename) {
        try {
            Path path = Paths.get(UPLOAD_DIR).resolve(filename);
            byte[] data = Files.readAllBytes(path);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(
                    ContentDisposition.attachment()
                            .filename(filename)
                            .build()
            );

            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
