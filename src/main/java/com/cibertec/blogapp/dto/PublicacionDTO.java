package com.cibertec.blogapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class PublicacionDTO {
    private Long id;
    private String titulo;
    private String contenido;
    private String nombreArchivo;
    private String tipoArchivo;
    private LocalDateTime fechaPublicacion;
    private Long usuarioId;
}

