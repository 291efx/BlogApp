package com.cibertec.blogapp.service.impl;

import com.cibertec.blogapp.dto.UsuarioDTO;
import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.repository.UsuarioRepository;
import com.cibertec.blogapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO dto) {
        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        u.setRol(dto.getRol());
        u.setFechaRegistro(LocalDateTime.now());
        Usuario guardado = usuarioRepository.save(u);
        return mapToDTO(guardado);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        Usuario u = usuarioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario no encontrado"));
        return mapToDTO(u);
    }

    @Override
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario u = usuarioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario no encontrado"));
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        // Si gestionas password aquí, hazlo con encriptación
        u.setPassword(dto.getPassword());
        u.setRol(dto.getRol());
        Usuario mod = usuarioRepository.save(u);
        return mapToDTO(mod);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(Usuario u) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(u.getId());
        dto.setNombre(u.getNombre());
        dto.setEmail(u.getEmail());
        dto.setRol(u.getRol());
        dto.setFechaRegistro(u.getFechaRegistro());
        return dto;
    }
}
