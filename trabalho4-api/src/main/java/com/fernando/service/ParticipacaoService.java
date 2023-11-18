package com.fernando.service;

import com.fernando.exception.EntidadeNaoEncontradaException;
import com.fernando.model.Participacao;
import com.fernando.repository.ParticipacaoRepository;
import com.fernando.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipacaoService {

    @Autowired
    private ParticipacaoRepository participacaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Participacao> recuperarParticipacoes() {
        return participacaoRepository.recuperarParticipacoesComUsuario();
    }

    public Participacao cadastrarParticipacao(Participacao participacao) {
        usuarioService.recuperarUsuarioPorId(participacao.getUsuario().getId());
        return participacaoRepository.save(participacao);
    }

    public Participacao recuperarParticipacaoPorId(Long id) {
        return participacaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Participacao número " + id + " não encontrado."));
    }

    public Participacao atualizaParticipacao(Participacao participacao) {
        // Somente com essa linha de código: return produtoRepository.save(produto);
        // Se não mudarmos a categoria serão executados esses 2 comandos:
        //    1. select p1_0.id,c1_0.id,c1_0.nome,c1_0.slug,p1_0.data_cadastro,p1_0.nome,p1_0.preco from produto p1_0 left join categoria c1_0 on c1_0.id=p1_0.categoria_id where p1_0.id=?
        //    2. update produto set categoria_id=?, data_cadastro=?, nome=?, preco=? where id=?
        // E se mudarmos a categoria serão executados esses 3 comandos:
        //    1. select p1_0.id,c1_0.id,c1_0.nome,c1_0.slug,p1_0.data_cadastro,p1_0.nome,p1_0.preco from produto p1_0 left join categoria c1_0 on c1_0.id=p1_0.categoria_id where p1_0.id=?
        //    2. select c1_0.id,c1_0.nome,c1_0.slug from categoria c1_0 where c1_0.id=?   (RECUPERA A NOVA CATEGORIA)
        //    3. update produto set categoria_id=?, data_cadastro=?, nome=?, preco=? where id=?

        // E com o código que escrevemos abaixo será EXATAMENTE IGUAL.

        // Se não mudarmos a categoria, o código abaixo executará esses 2 comandos:
        //    1. Para recuperarProdutoPorId(): select p1_0.id,c1_0.id,c1_0.nome,c1_0.slug,p1_0.data_cadastro,p1_0.nome,p1_0.preco from produto p1_0 left join categoria c1_0 on c1_0.id=p1_0.categoria_id where p1_0.id=?
        //    2. E para o método save(): update produto set categoria_id=?, data_cadastro=?, nome=?, preco=? where id=?

        // E se mudarmos a categoria:
        // Sem o findById() abaixo esse método executaria esses 3 comandos:
        //    1. Para recuperarProdutoPorId(): select p1_0.id,c1_0.id,c1_0.nome,c1_0.slug,p1_0.data_cadastro,p1_0.nome,p1_0.preco from produto p1_0 left join categoria c1_0 on c1_0.id=p1_0.categoria_id where p1_0.id=?
        // E para o método save(), os 2 abaixo:
        //    2. Para recuperar a nova categoria: select c1_0.id,c1_0.nome,c1_0.slug from categoria c1_0 where c1_0.id=?
        //    3. Para atualizar o produto: update produto set categoria_id=?, data_cadastro=?, nome=?, preco=? where id=?

        // E se acrescentarmos o findById() abaixo, então ficará assim:
        //    1. Para recuperarProdutoPorId(): select p1_0.id,c1_0.id,c1_0.nome,c1_0.slug,p1_0.data_cadastro,p1_0.nome,p1_0.preco
        //                                     from produto p1_0 left join categoria c1_0 on c1_0.id=p1_0.categoria_id where p1_0.id=?
        //    2. Para o findById() que recupera a nova categoria: select c1_0.id,c1_0.nome,c1_0.slug from categoria c1_0 where c1_0.id=?
        //    3. E para o save() que atualiza o produto: update produto set categoria_id=?, data_cadastro=?, nome=?, preco=? where id=?

        Participacao umaParticipacao = recuperarParticipacaoPorId(participacao.getId());
        if(!(participacao.getUsuario().getId().equals(umaParticipacao.getUsuario().getId()))) {
            usuarioRepository.findById(participacao.getUsuario().getId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException(
                            "Usuario número " + participacao.getUsuario().getId() + " não encontrado."));
        }
        return participacaoRepository.save(participacao);
    }

//    @Transactional
//    public Produto atualizaProduto(Produto produto) {
//        produtoRepository.recuperarProdutoPorIdComLock(produto.getId())
//            .orElseThrow(() -> new EntidadeNaoEncontradaException(
//                "Produto número " + produto.getId() + " não encontrado."));
//        return produtoRepository.save(produto);
//    }

//    @Transactional
//    public Produto atualizaProduto(Produto produto) {
//        Produto umProduto = produtoRepository.recuperarProdutoPorIdComLock(produto.getId());
//        if (umProduto == null)
//            throw new EntidadeNaoEncontradaException("Produto número " + produto.getId() + " não encontrado.");
//        return produtoRepository.save(produto);
//    }

//    @Transactional
//    public Produto atualizaProduto(Produto produto) {
//        Produto umProduto = produtoRepository.recuperarProdutoPorIdComLock(produto.getId())
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(
//                        "Produto número " + produto.getId() + " não encontrado."));
//        umProduto.setNome(produto.getNome());
//        umProduto.setPreco(produto.getPreco());
//        umProduto.setDataCadastro(produto.getDataCadastro());
//        return umProduto;
//    }

    public void removerParticipacao(Long id) {
        recuperarParticipacaoPorId(id);
        participacaoRepository.deleteById(id);
    }

    public Participacao removerParticipacao(Participacao participacao) {
        Participacao outraParticipacao = recuperarParticipacaoPorId(participacao.getId());
        participacaoRepository.deleteById(outraParticipacao.getId());
        return outraParticipacao;
    }

    public List<Participacao> recuperarParticipacaoPorUsuario(Long id) {
        return participacaoRepository.findByUsuarioId(id);
    }

    public List<Participacao> recuperarParticipacoesPorSlugDoUsuario(String slug) {
        return participacaoRepository.findByUsuarioSlug(slug);
    }

    public Page<Participacao> recuperarParticipacoesPaginadas(String nome, Pageable pageable) {
        return participacaoRepository.recuperarParticipacoesPaginadas(nome, pageable);
    }

    public void atualizarParticipacao(Long id, String nome) {
        participacaoRepository.atualizarParticipacao(id, nome);
    }
}
