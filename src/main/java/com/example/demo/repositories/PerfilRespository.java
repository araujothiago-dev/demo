package com.example.demo.repositories;

import com.example.demo.models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRespository extends JpaRepository<Perfil, Long> {
}
