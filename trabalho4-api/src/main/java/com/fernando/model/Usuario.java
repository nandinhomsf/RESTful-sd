package com.fernando.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String slug;

       // select c from Categoria c left outer join fetch c.produtos where c.id = :id
//    @OneToMany(mappedBy = "categoria")
//    private List<Produto> produtos = new ArrayList<>();

    public Usuario(String nome, String slug) {
        this.nome = nome;
        this.slug = slug;
    }
}
