package com.cibertec.blogapp.dto;

import lombok.*;

@Getter
@Setter
public class ComentarioDTO {
    private Long id;
    private String contenido;
    private Long usuarioId;
    private Long publicacionId;
}

