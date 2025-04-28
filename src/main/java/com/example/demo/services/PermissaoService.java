package com.example.demo.services;

import com.example.demo.dto.PermissaoResponseDTO;
import com.example.demo.models.Permissao;

import java.util.List;

public interface PermissaoService {
    PermissaoResponseDTO create(Permissao permissao);
    List<PermissaoResponseDTO> findAll();
}
