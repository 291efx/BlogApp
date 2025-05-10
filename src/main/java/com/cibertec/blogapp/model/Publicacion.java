package com.cibertec.blogapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "publicaciones")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String contenido;

    @Column(name="nombre_archivo")
    private String nombreArchivo;
    @Column(name="tipo_archivo")
    private String tipoArchivo;
    @Column(name="ruta_archivo")
    private String rutaArchivo;

    @Column(name="fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}