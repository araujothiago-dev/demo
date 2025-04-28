package com.example.demo.services;

import com.example.demo.dto.PerfilResponseDTO;
import com.example.demo.models.Perfil;
import com.example.demo.dto.UsuarioResponseDTO;

import java.util.List;

public interface PerfilService {
    PerfilResponseDTO create(Perfil usuario);
    List<PerfilResponseDTO> findAll();
}
