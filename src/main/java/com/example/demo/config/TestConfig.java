package com.example.demo.config;

import com.example.demo.dto.PerfilResponseDTO;
import com.example.demo.dto.PermissaoResponseDTO;
import com.example.demo.models.Perfil;
import com.example.demo.models.Permissao;
import com.example.demo.repositories.PerfilRespository;
import com.example.demo.repositories.PermissaoRespository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRespository perfilRespository;

    @Autowired
    private PermissaoRespository permissaoRespository;

    @Override
    public void run(String... args) throws Exception {
        Permissao ler_usuario = new Permissao(null,"ler_usuario");
        Permissao modificar_usuario = new Permissao(null, "modificar_usuario");

        permissaoRespository.saveAll(Arrays.asList(ler_usuario, modificar_usuario));

        Perfil admin = new Perfil(null, "admin", Arrays.asList(ler_usuario, modificar_usuario));
        Perfil comum = new Perfil(null, "comum", Arrays.asList(ler_usuario));

        perfilRespository.saveAll(Arrays.asList(admin, comum));
    }

}
