package com.cibertec.blog.security;

import com.cibertec.blog.model.Usuario;
import com.cibertec.blog.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (!usuarioOptional.isPresent()) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
        }

        Usuario usuario = usuarioOptional.get();

        return new User(
                usuario.getEmail(), // Usamos el email como username
                usuario.getPassword(), // Contraseña encriptada
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol())) // Agregar rol
        );
    }
}
