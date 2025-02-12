package com.cibertec.blog.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionDTO {
    private Long id;
    private String titulo;
    private String contenido;
    private LocalDateTime fechaPublicacion;
    private String autor;

}


