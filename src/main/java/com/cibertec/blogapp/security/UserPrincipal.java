package com.cibertec.blogapp.security;

import com.cibertec.blogapp.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador de nuestra entidad Usuario al contrato UserDetails de Spring Security.
 */
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;    // aquí guardamos el email
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id,
                         String username,
                         String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Convierte un Usuario de la BD en un UserPrincipal con sus roles.
     */
    public static UserPrincipal build(Usuario usuario) {
        List<GrantedAuthority> roles = usuario.getRol() == null
                ? List.of()
                : List.of(new SimpleGrantedAuthority(usuario.getRol()));

        return new UserPrincipal(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getPassword(),
                roles
        );
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * El nombre de usuario que Spring Security usará es el email.
     */
    @Override
    public String getUsername() {
        return username;
    }

    // Opciones fijas para este ejemplo (sin bloqueo ni expiración)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
