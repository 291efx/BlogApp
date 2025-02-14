package com.cibertec.blogapp.service;

import com.cibertec.blogapp.dto.UsuarioDTO;
import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.repository.PublicacionRepository;
import com.cibertec.blogapp.repository.UsuarioRepository;
import com.cibertec.blogapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PublicacionRepository publicacionRepository; // Agregamos el repositorio de publicaciones

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        // Convertir lista de Usuario a lista de UsuarioDTO
        return usuarios.stream().map(this::convertirADTO).toList();
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<String> nombresArchivos = publicacionRepository.findNombresArchivosByUsuarioId(id);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setFechaRegistro(usuario.getFechaRegistro());
        usuarioDTO.setNombresArchivos(nombresArchivos);

        return usuarioDTO;
    }

    @Override
    public Usuario obtenerEntidadPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }


    // ✅ Método para convertir Usuario a UsuarioDTO
    private UsuarioDTO convertirADTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setFechaRegistro(usuario.getFechaRegistro());

        // Aquí debes colocar la lógica real para obtener nombres de archivos
        List<String> nombresArchivos = publicacionRepository.findNombresArchivosByUsuarioId(usuario.getId());
        usuarioDTO.setNombresArchivos(nombresArchivos);


        return usuarioDTO;
    }
    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

}
