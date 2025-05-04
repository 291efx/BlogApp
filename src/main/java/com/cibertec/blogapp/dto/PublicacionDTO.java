package com.cibertec.blogapp.dto;

import lombok.*;

@Getter
@Setter
public class PublicacionDTO {
    private Long id;
    private String titulo;
    private String contenido;
    private String nombreArchivo;
    private String tipoArchivo;
    private byte[] archivo;
    private Long usuarioId;
}


