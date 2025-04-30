package com.example.demo.services;

import com.example.demo.dto.PerfilResponseDTO;
import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.models.Permissao;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO create(UsuarioRequestDTO usuario);
    List<UsuarioResponseDTO> findAll();
    UsuarioResponseDTO findById(Long id);
    UsuarioResponseDTO findByEmail(String email);
    void update(UsuarioRequestDTO usuario, Long id);
    void delete(Long id);
    PerfilResponseDTO findPerfilByUsuarioId(Long id);
    List<Permissao> findPermissoesByUsuarioId(Long id);

}
