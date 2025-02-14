package com.cibertec.blogapp.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private LocalDateTime fechaRegistro;
    private List<String> nombresArchivos;
}
