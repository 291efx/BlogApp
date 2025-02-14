package com.cibertec.blogapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Publicacion> publicaciones;
}
