package com.cibertec.blogapp.service.impl;

import com.cibertec.blogapp.dto.PublicacionDTO;
import com.cibertec.blogapp.dto.UsuarioDTO;
import com.cibertec.blogapp.model.Publicacion;
import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.repository.PublicacionRepository;
import com.cibertec.blogapp.repository.UsuarioRepository;
import com.cibertec.blogapp.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public PublicacionDTO crearConArchivo(String titulo,
                                          String contenido,
                                          Long usuarioId,
                                          MultipartFile archivo) {
        try {
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            String ruta = guardarArchivo(archivo);

            Publicacion pub = new Publicacion();
            pub.setTitulo(titulo);
            pub.setContenido(contenido);
            pub.setFechaPublicacion(LocalDateTime.now());
            pub.setNombreArchivo(archivo.getOriginalFilename());
            pub.setTipoArchivo(archivo.getContentType());
            pub.setRutaArchivo(ruta);
            pub.setUsuario(usuario);

            Publicacion guardada = publicacionRepository.save(pub);
            return mapToDTO(guardada);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo", e);
        }
    }

    private String guardarArchivo(MultipartFile archivo) throws IOException {
        if (archivo == null || archivo.isEmpty()) {
            return null;
        }
        Path carpeta = Paths.get(UPLOAD_DIR);
        if (!Files.exists(carpeta)) {
            Files.createDirectories(carpeta);
        }
        String nombreUnico = UUID.randomUUID() + "_" + archivo.getOriginalFilename();
        Path destino = carpeta.resolve(nombreUnico);
        Files.write(destino, archivo.getBytes(), StandardOpenOption.CREATE);
        return UPLOAD_DIR + nombreUnico;
    }

    @Override
    public List<PublicacionDTO> listarTodas() {
        return publicacionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PublicacionDTO> obtenerPorId(Long id) {
        return publicacionRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Override
    public void eliminar(Long id) {
        publicacionRepository.deleteById(id);
    }

    @Override
    public List<PublicacionDTO> listarPorUsuario(Long usuarioId) {
        return publicacionRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PublicacionDTO actualizarPublicacion(Long id, PublicacionDTO datos) {
        Publicacion pub = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
        // actualizar sólo texto
        pub.setTitulo(datos.getTitulo());
        pub.setContenido(datos.getContenido());
        Publicacion mod = publicacionRepository.save(pub);
        return mapToDTO(mod);
    }


    // → CONVERSIÓN A DTO
    private PublicacionDTO mapToDTO(Publicacion pub) {
        PublicacionDTO dto = new PublicacionDTO();
        dto.setId(pub.getId());
        dto.setTitulo(pub.getTitulo());
        dto.setContenido(pub.getContenido());
        dto.setNombreArchivo(pub.getNombreArchivo());
        dto.setTipoArchivo(pub.getTipoArchivo());
        dto.setRutaArchivo(pub.getRutaArchivo());
        dto.setFechaPublicacion(pub.getFechaPublicacion());
        dto.setUsuario(mapUsuarioToDTO(pub.getUsuario()));
        return dto;
    }

    // → CONVERSIÓN DE USUARIO a UsuarioDTO
    private UsuarioDTO mapUsuarioToDTO(Usuario usuario) {
        UsuarioDTO u = new UsuarioDTO();
        u.setId(usuario.getId());
        u.setNombre(usuario.getNombre());
        u.setEmail(usuario.getEmail());
        u.setRol(usuario.getRol());
        u.setFechaRegistro(usuario.getFechaRegistro());
        return u;
    }


}