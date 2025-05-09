package com.cibertec.blogapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
        		.requestMatchers("/api/**").permitAll()  // <- permite listar sin login
                                //.requestMatchers("/api/comentarios/**").hasAnyRole("USER", "ADMIN")
                                //.requestMatchers("/api/publicaciones/**").hasAnyRole("USER", "ADMIN")
                                //.requestMatchers("/api/usuarios/**").permitAll() 
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
		
		
		return http.build();
	}
	
	
	
	/*
	//este metodo sirve para encriptar
	public static void main(String[] args) {
		System.out.println("Password: " + new BCryptPasswordEncoder().encode("admin"));
	}
	
	*/
}
