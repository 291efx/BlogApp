package com.cibertec.blogapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String rol;
    private LocalDateTime fechaRegistro;
}
