package com.example.demo.services.impl;

import com.example.demo.dto.PerfilResponseDTO;
import com.example.demo.dto.PermissaoResponseDTO;
import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.models.Perfil;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.PerfilRespository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRespository perfilRespository;

    @Override
    public UsuarioResponseDTO create(UsuarioRequestDTO usuarioDto) {
        Usuario emailExiste = usuarioRepository.findByEmail(usuarioDto.getEmail());

        if (emailExiste != null) {
            throw new RuntimeException("E-mail já cadastrado. ");
        }

        Perfil perfil = perfilRespository.findById(usuarioDto.getPerfil_id()).orElseThrow(() -> new RuntimeException("Perfil não encontrado. "));

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPerfil(perfil);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                new PerfilResponseDTO(
                        usuarioSalvo.getPerfil().getId(),
                        usuarioSalvo.getPerfil().getNome(),
                        usuarioSalvo.getPerfil().getPermissoes().stream()
                                .map(permissao -> new PermissaoResponseDTO(permissao.getId(), permissao.getNome()))
                                .toList()
                )
        );

    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarios =  usuarioRepository.findAll();

        return usuarios.stream()
                .map(usuario -> new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        new PerfilResponseDTO(
                                usuario.getPerfil().getId(),
                                usuario.getPerfil().getNome(),
                                usuario.getPerfil().getPermissoes().stream()
                                        .map(permissao -> new PermissaoResponseDTO(
                                                permissao.getId(),
                                                permissao.getNome()
                                        ))
                                        .toList()
                        )
                ))
                .collect(Collectors.toList());
    }
}
