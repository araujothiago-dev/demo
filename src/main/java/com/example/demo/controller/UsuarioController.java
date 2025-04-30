package com.example.demo.controller;

import com.example.demo.dto.PerfilResponseDTO;
import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.models.Permissao;
import com.example.demo.repositories.PerfilRespository;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(
            @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuario = usuarioService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
        List<UsuarioResponseDTO> usuarios = usuarioService.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/id")
    public ResponseEntity<UsuarioResponseDTO> findById(
            @RequestParam(name = "id")  Long id) {
        UsuarioResponseDTO usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/email")
    public ResponseEntity<UsuarioResponseDTO> findByEmail(
            @RequestParam String email) {
        UsuarioResponseDTO usuario = usuarioService.findByEmail(email);
        return ResponseEntity.ok().body(usuario);
    }

    @PatchMapping
    public ResponseEntity<?> update(
            @RequestParam(name = "id") Long id,
            @RequestBody UsuarioRequestDTO dto
    ) throws SQLException {
        usuarioService.update(dto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestParam(name = "id") Long id
    ) throws SQLException{
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/perfil")
    public ResponseEntity<PerfilResponseDTO> findPerfilByUsuarioId(
            @RequestParam(name = "id") Long id
    ) throws SQLException {
        PerfilResponseDTO perfil = usuarioService.findPerfilByUsuarioId(id);
        return ResponseEntity.ok().body(perfil);
    }

    @GetMapping("/permissoes")
    public ResponseEntity<List<Permissao>> findPermissaoByUsuarioId(
            @RequestParam(name = "id") Long id
    ) throws SQLException {
        List<Permissao> permissoes = usuarioService.findPermissoesByUsuarioId(id);
        return ResponseEntity.ok().body(permissoes);
    }
}
