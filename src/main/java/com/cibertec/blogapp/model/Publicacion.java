package com.cibertec.blogapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "publicaciones")
@Getter
@Setter
@ToString(exclude = "archivo")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Lob
    @Column(name = "archivo", columnDefinition = "LONGBLOB")
    private byte[] archivo;

    @Column(name = "nombre_archivo", nullable = true)
    private String nombreArchivo;

    @Column(name = "tipo_archivo", nullable = true)
    private String tipoArchivo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore  // 🔴 Esto evita la recursión infinita
    private Usuario usuario;

    @PrePersist
    protected void onCreate() {
        fechaPublicacion = LocalDateTime.now();
    }
}

