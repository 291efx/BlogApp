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
                        .requestMatchers("/", "/index", "/login", "/registro", "/publicacion_detalle", "/publicacion_form")
                        .permitAll()  // Permite acceso sin autenticación a estas rutas
                        .requestMatchers("/img/**", "/styles.css", "/script.js")
                        .permitAll()  // Permite acceso a archivos estáticos
                        .anyRequest().authenticated()  // Resto de rutas requieren autenticación
                )
                .formLogin(login -> login
                        .loginPage("/login")  // Página de inicio de sesión personalizada
                        .defaultSuccessUrl("/index", true)  // Redirigir a index después de iniciar sesión
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // Deshabilitar CSRF si es necesario

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
