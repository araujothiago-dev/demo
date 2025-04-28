package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private PerfilResponseDTO pefil;
}
