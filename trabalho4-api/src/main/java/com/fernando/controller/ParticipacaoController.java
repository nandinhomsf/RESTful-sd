package com.fernando.controller;

import com.fernando.model.Participacao;
import com.fernando.service.ParticipacaoService;
import com.fernando.util.ParticipacoesPaginadas;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@AllArgsConstructor
@RequestMapping("participacoes")
public class ParticipacaoController {

    private final ParticipacaoService participacaoService;

    @GetMapping       // http://localhost:8080/produtos
    public List<Participacao> recuperarParticipacoes() {
        return participacaoService.recuperarParticipacoes();
    }

    @PostMapping
    public Participacao cadastrarParticipacao(@RequestBody Participacao participacao) {
        return participacaoService.cadastrarParticipacao(participacao);
    }
    // http://localhost:8080/produtos/1

//    @GetMapping("{idProduto}")
//    public ResponseEntity<?> recuperarProdutoPorId(@PathVariable("idProduto") Long id) {
//        try {
//            Produto produto = produtoService.recuperarProdutoPorId(id);
//            return new ResponseEntity<>(produto, HttpStatus.OK);
//        }
//        catch(EntidadeNaoEncontradaException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("{idParticipacao}")       // http://localhost:8080/produtos/1
    public Participacao recuperarParticipacaoPorId(@PathVariable("idParticipacao") Long id) {
        return participacaoService.recuperarParticipacaoPorId(id);
    }

    @PutMapping                  // http://localhost:8080/produtos
    public Participacao atualizarParticipacao(@RequestBody Participacao participacao) {
        return participacaoService.atualizaParticipacao(participacao);
    }

                                   // http://localhost:8080/produtos/1
    @DeleteMapping("{idParticipacao}")
    public void removerParticipacao(@PathVariable("idParticipacao") Long id) {
        participacaoService.removerParticipacao(id);
    }

    @PostMapping("remover")
    public Participacao removerParticipacaoEnviandoParticipacaoNoRequest(@RequestBody Participacao participacao) {
        return participacaoService.removerParticipacao(participacao);
    }


    @GetMapping("usuario/{slugUsuario}")         // http://localhost:8080/produtos/categoria/frutas
    public List<Participacao> recuperarParticipacoesPorSlugDoUsuario(@PathVariable("slugUsuario") String slug) {
        return participacaoService.recuperarParticipacoesPorSlugDoUsuario(slug);
    }

    @GetMapping("usuario")   // http://localhost:8080/produtos/categoria?idCategoria=1
    public List<Participacao> recuperarParticipacaoPorUsuarioV2(@RequestParam("idUsuario") Long id) {
        if (id.equals(-1L))
            return participacaoService.recuperarParticipacoes();
        return participacaoService.recuperarParticipacaoPorUsuario(id);
    }

    @GetMapping("paginacao")   // http://localhost:8080/produtos/paginacao?pagina=0&tamanho=5
    public ParticipacoesPaginadas recuperarParticipacoesPaginadas(
            @RequestParam(name="pagina", defaultValue = "0") int pagina,
            @RequestParam(name="tamanho", defaultValue = "3") int tamanho,
            @RequestParam(name="nome", defaultValue = "") String nome
    ) {


        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Participacao> paginaDeParticipacao = participacaoService.recuperarParticipacoesPaginadas(nome, pageable);
        ParticipacoesPaginadas participacoesPaginadas = new ParticipacoesPaginadas(
                paginaDeParticipacao.getTotalElements(),
                paginaDeParticipacao.getTotalPages(),
                paginaDeParticipacao.getNumber(),
                paginaDeParticipacao.getContent());
        return participacoesPaginadas;
    }

    // alterar participacao
    @PutMapping("alterar/{idParticipacao}")
    public void atualizarParticipacao(@PathVariable("idParticipacao") Long id, @RequestBody String nome) {
        participacaoService.atualizarParticipacao(id, nome);
    }

}
