package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarios;

    @ManyToMany
    @JoinTable(
            name = "perfil_permissao",
            joinColumns = @JoinColumn(name = "perfil_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id")
    )
    private List<Permissao> permissoes = new ArrayList<>();

    public Perfil(Long id, String nome, List<Permissao> permissoes) {
        this.id = id;
        this.nome = nome;
        this.permissoes = permissoes;
    }
}
