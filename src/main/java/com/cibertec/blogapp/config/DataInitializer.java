package com.cibertec.blogapp.config;

import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin@example.com";
        if (usuarioRepository.findByEmail(adminEmail).isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Administrador");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("ChangeMe123!"));  // ¡cámbialo enseguida!
            admin.setRol("ROLE_ADMIN");
            admin.setFechaRegistro(LocalDateTime.now());
            usuarioRepository.save(admin);
            System.out.println(">> Admin inicial creado: " + adminEmail);
        }
    }
}
