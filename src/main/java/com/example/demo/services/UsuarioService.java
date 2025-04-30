package com.example.demo.services;

import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioResponseDTO create(UsuarioRequestDTO usuario);
    List<UsuarioResponseDTO> findAll();
    UsuarioResponseDTO findById(Long id);
    UsuarioResponseDTO findByEmail(String email);
    void update(UsuarioRequestDTO usuario, Long id);
    void delete(Long id);
}
