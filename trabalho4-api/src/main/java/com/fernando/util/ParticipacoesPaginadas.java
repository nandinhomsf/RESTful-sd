package com.fernando.util;

import com.fernando.model.Participacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class ParticipacoesPaginadas {
    private long totalDeItens;
    private int totalDePaginas;
    private int paginaCorrente;
    private List<Participacao> itens;
}
