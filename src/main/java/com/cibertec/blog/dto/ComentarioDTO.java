package com.cibertec.blog.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class ComentarioDTO {
    private Long id;
    private String contenido;
    private String autor; // Nombre del usuario que comenta
    private LocalDateTime fechaComentario;
}

