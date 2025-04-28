package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PerfilResponseDTO {
    private Long id;
    private String nome;
    private List<PermissaoResponseDTO> permissoes;
}
