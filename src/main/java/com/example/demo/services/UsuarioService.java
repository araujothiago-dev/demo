package com.example.demo.services;

import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO create(UsuarioRequestDTO usuario);
    List<UsuarioResponseDTO> findAll();
}
