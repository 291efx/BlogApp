package com.cibertec.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/registro", "/styles.css", "/login", "/error").permitAll() // Permitir acceso libre a estas rutas
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Acceso solo para ADMIN
                        .requestMatchers("/publicacion/**").hasAnyRole("USER", "ADMIN") // Usuarios autenticados pueden ver publicaciones
                        .anyRequest().authenticated() // Todo lo demás requiere autenticación
                )
                .formLogin(login -> login
                        .loginPage("/login")  // Página de login
                        .loginProcessingUrl("/procesarLogin")  // Procesamiento del login
                        .defaultSuccessUrl("/", true)  // Redirigir al inicio tras login exitoso
                        .failureUrl("/login?error=true")  // Manejo de error de autenticación
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // Redirigir a login tras cerrar sesión
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
