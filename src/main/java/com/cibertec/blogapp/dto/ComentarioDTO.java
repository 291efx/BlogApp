package com.cibertec.blogapp.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class ComentarioDTO {
    private Long id;
    private String contenido;
    private LocalDateTime fechaCreacion;
    private Long usuarioId;
    private Long publicacionId;
}
