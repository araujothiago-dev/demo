package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class PerfilResponseDTO {
    private Long id;
    private String nome;
    private List<PermissaoResponseDTO> permissoes;

    public PerfilResponseDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
