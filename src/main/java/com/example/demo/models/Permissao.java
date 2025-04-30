package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @ManyToMany(mappedBy = "permissoes")
    @JsonIgnore
    private List<Perfil> perfis = new ArrayList<>();

    public Permissao(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
