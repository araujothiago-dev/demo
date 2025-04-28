package com.example.demo.repositories;

import com.example.demo.models.Perfil;
import com.example.demo.models.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRespository  extends JpaRepository<Permissao, Long> {
}
