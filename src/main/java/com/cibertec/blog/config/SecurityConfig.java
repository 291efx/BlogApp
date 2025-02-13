package com.cibertec.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 🔴 DESACTIVAR RESTRICCIONES: Permitir todo
                )
                .csrf(csrf -> csrf.disable()) // ⚠️ DESACTIVAR CSRF
                .formLogin(form -> form.disable()) // 🔴 DESACTIVAR FORM LOGIN
                .logout(logout -> logout.disable()); // 🔴 DESACTIVAR LOGOUT

        return http.build();
    }
}
