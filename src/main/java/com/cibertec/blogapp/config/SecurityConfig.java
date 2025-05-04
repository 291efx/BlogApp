package com.cibertec.blogapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.cibertec.blogapp.security.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // 1) BCrypt encoder para todas las contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2) Le decimos a Spring que use nuestro UserDetailsService + BCrypt
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    // 3) Definimos las reglas de acceso endpoint por endpoint
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-ui/index.html",
                                "/swagger-ui/index.html/**",
                                "/swagger-resources/**",
                                "/configuration/**",
                                "/webjars/**"
                        ).permitAll()

                        // --- USUARIOS ---
                        // Registro público
                        .requestMatchers(HttpMethod.POST,   "/api/usuarios/registrar").permitAll()
                        // Listar todos (solo ADMIN)
                        .requestMatchers(HttpMethod.GET,    "/api/usuarios").hasRole("ADMIN")
                        // Ver perfil (cualquier autenticado)
                        .requestMatchers(HttpMethod.GET,    "/api/usuarios/*").authenticated()
                        // Editar perfil (propio o admin; validación de “propiedad” en servicio)
                        .requestMatchers(HttpMethod.PUT,    "/api/usuarios/*").authenticated()
                        // Borrar usuario (solo ADMIN)
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios/*").hasRole("ADMIN")

                        // --- PUBLICACIONES ---
                        // Crear con archivo (USER o ADMIN)
                        .requestMatchers(HttpMethod.POST,   "/api/publicaciones/crear-con-archivo")
                        .hasAnyRole("USER","ADMIN")
                        // Listar, ver by id, por usuario y descargar (público)
                        .requestMatchers(HttpMethod.GET,    "/api/publicaciones").permitAll()
                        .requestMatchers(HttpMethod.GET,    "/api/publicaciones/*").permitAll()
                        .requestMatchers(HttpMethod.GET,    "/api/publicaciones/usuario/*").permitAll()
                        .requestMatchers(HttpMethod.GET,    "/api/publicaciones/descargar/*").permitAll()
                        // Editar y borrar (propias o admin; propiedad validada en servicio)
                        .requestMatchers(HttpMethod.PUT,    "/api/publicaciones/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/publicaciones/*").authenticated()

                        // --- COMENTARIOS ---
                        // Crear comentario (USER o ADMIN)
                        .requestMatchers(HttpMethod.POST,   "/api/comentarios/crear")
                        .hasAnyRole("USER","ADMIN")
                        // Listar y consultar (público)
                        .requestMatchers(HttpMethod.GET,    "/api/comentarios").permitAll()
                        .requestMatchers(HttpMethod.GET,    "/api/comentarios/*").permitAll()
                        .requestMatchers(HttpMethod.GET,    "/api/comentarios/usuario/*").permitAll()
                        .requestMatchers(HttpMethod.GET,    "/api/comentarios/publicacion/*").permitAll()
                        // Editar y borrar (propios o admin; propiedad validada en servicio)
                        .requestMatchers(HttpMethod.PUT,    "/api/comentarios/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/comentarios/*").authenticated()

                        // Cualquier otra ruta no declarada queda denegada
                        .anyRequest().denyAll()

                )
                // Usamos HTTP Basic para autenticación
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
