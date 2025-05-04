package com.cibertec.blogapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PublicacionDTO {
    private Long id;
    private String titulo;
    private String contenido;
    private String nombreArchivo;
    private String tipoArchivo;
    private String rutaArchivo;
    private LocalDateTime fechaPublicacion;
    private UsuarioDTO usuario;
}
