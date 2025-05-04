package com.cibertec.blogapp.dto;

import lombok.*;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
}

