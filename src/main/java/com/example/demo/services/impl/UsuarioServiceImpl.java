package com.example.demo.services.impl;

import com.example.demo.dto.PerfilResponseDTO;
import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UnicoException;
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
    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {
        Usuario emailExiste = usuarioRepository.findByEmail(dto.getEmail());

        if (emailExiste != null) {
            throw new UnicoException("E-mail já cadastrado. ");
        }

        Perfil perfil = perfilRespository.findById(dto.getPerfil_id()).orElseThrow(() -> new NotFoundException("Perfil não encontrado. "));

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setPerfil(perfil);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                new PerfilResponseDTO(
                        usuarioSalvo.getPerfil().getId(),
                        usuarioSalvo.getPerfil().getNome()
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
                                usuario.getPerfil().getNome()
                        )
                ))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado. "));

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                new PerfilResponseDTO(
                        usuario.getPerfil().getId(),
                        usuario.getPerfil().getNome()
                )
        );
    }

    @Override
    public UsuarioResponseDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado. ");
        }

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                new PerfilResponseDTO(
                        usuario.getPerfil().getId(),
                        usuario.getPerfil().getNome()
                )
        );
    }

    @Override
    public void update(
            UsuarioRequestDTO dto,
            Long id) throws RuntimeException {
        Usuario usuarioExiste = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado. "));

        Perfil perfil = perfilRespository.findById(dto.getPerfil_id()).orElseThrow(() -> new NotFoundException("Perfil não encontrado. "));

        if(dto.getNome() != null)
            usuarioExiste.setNome(dto.getNome());

        if(dto.getEmail() != null)
            usuarioExiste.setEmail(dto.getEmail());

        if(dto.getPerfil_id() != null)
            usuarioExiste.setPerfil(perfil);

        usuarioRepository.save(usuarioExiste);
    }

    @Override
    public void delete(Long id) throws RuntimeException{
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado. "));
        usuarioRepository.delete(usuario);
    }
}
