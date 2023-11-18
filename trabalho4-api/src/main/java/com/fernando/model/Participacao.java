package com.fernando.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Participacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A 'Imagem' deve ser informada.")
    private String imagem;

    @NotEmpty(message = "O 'Nome' deve ser informado.")
    private String nome;

    @NotNull(message = "O 'Credito' deve ser informado.")
    @DecimalMin(inclusive = true, value="5", message = "O 'Preço' deve ser maior ou igual a 5.")
    private BigDecimal credito;

    @NotNull(message = "O 'Xp' deve ser informado.")
    @DecimalMin(inclusive = true, value="1", message = "O 'Preço' deve ser maior ou igual a 1.")
    private Integer xp;

    @NotNull(message = "A 'Data de Cadastro' deve ser informada.")
    @Column(name = "DATA_CADASTRO")
    private LocalDate dataCadastro;

//    @Version
//    private int versao;

    @ManyToOne
    private Usuario usuario;

    public Participacao(String imagem,
                   Usuario usuario,
                   String nome,
                   LocalDate dataCadastro,
                   Integer xp,
                   BigDecimal credito) {
        this.imagem = imagem;
        this.usuario = usuario;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
        this.xp = xp;
        this.credito = credito;
    }
}
