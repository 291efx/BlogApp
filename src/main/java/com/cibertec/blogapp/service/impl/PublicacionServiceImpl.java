package com.cibertec.blogapp.service.impl;


import com.cibertec.blogapp.model.Publicacion;
import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.repository.PublicacionRepository;
import com.cibertec.blogapp.repository.UsuarioRepository;
import com.cibertec.blogapp.service.PublicacionService;
import com.cibertec.blogapp.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PublicacionServiceImpl implements PublicacionService{

    @Autowired
    private StorageService storageService;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todas las publicaciones
    public ResponseEntity<?> listarTodas() {
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        return publicaciones.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(publicaciones);
    }

    // Obtener por ID
    public ResponseEntity<?> obtenerPorId(Long id) {
        return publicacionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar por usuario
    public ResponseEntity<?> listarPorUsuario(Long usuarioId) {
        List<Publicacion> publicaciones = publicacionRepository.findByUsuarioId(usuarioId);
        return publicaciones.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(publicaciones);
    }

    // Actualizar publicación
    public ResponseEntity<?> actualizar(Long id, Publicacion publicacion) {
        return publicacionRepository.findById(id)
                .map(existing -> {
                    existing.setTitulo(publicacion.getTitulo());
                    existing.setContenido(publicacion.getContenido());
                    return ResponseEntity.ok(publicacionRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar publicación
    public ResponseEntity<?> eliminar(Long id) {
        if (!publicacionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        publicacionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Crear publicación con archivo
    public ResponseEntity<?> crearPublicacion(String titulo, String contenido, MultipartFile file, Long usuarioId) {
        try {
            Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
            		() -> new IllegalArgumentException("Usuario no encontrado"));

            String rutaGuardada = guardarArchivo(file);

            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo(titulo);
            publicacion.setContenido(contenido);
            publicacion.setNombreArchivo(file.getOriginalFilename());
            publicacion.setTipoArchivo(file.getContentType());
            publicacion.setRutaArchivo("uploads/"+rutaGuardada);
            publicacion.setFechaPublicacion(LocalDateTime.now());
            publicacion.setUsuario(usuario);

            publicacionRepository.save(publicacion);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje", "Publicación creada con éxito"));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el archivo: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado: " + e.getMessage());
        }
    }

    public ResponseEntity<?> actualizarConArchivo(Long id, String titulo, String contenido, MultipartFile archivo) {
        Optional<Publicacion> optional = publicacionRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicación no encontrada");
        }

        Publicacion pub = optional.get();
        pub.setTitulo(titulo);
        pub.setContenido(contenido);

        if (archivo != null && !archivo.isEmpty()) {
            // guardar archivo en el disco y actualizar ruta
            String nuevaRuta = null;
			try {
				nuevaRuta = guardarArchivo(archivo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            pub.setRutaArchivo("uploads/"+nuevaRuta);
        }

        publicacionRepository.save(pub);
        return ResponseEntity.ok(pub);
    }

    
    // Método auxiliar para guardar el archivo
    private String guardarArchivo(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("El archivo está vacío");
        }

        return storageService.store(file); // ya se encarga del nombre único
    }
}
