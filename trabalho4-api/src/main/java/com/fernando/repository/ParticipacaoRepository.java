package com.fernando.repository;

import com.fernando.model.Participacao;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParticipacaoRepository extends JpaRepository<Participacao, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Participacao p where p.id = :id")
//  Produto recuperarProdutoPorIdComLock(@Param("id") Long id);
    Optional<Participacao> recuperarParticipacaoPorIdComLock(@Param("id") Long id);

    @Query("select p from Participacao p left join fetch p.usuario u where u.id = :id")
    // @Query("select p from Produto p where p.categoria.id = :id")
    List<Participacao> findByUsuarioId(Long id);

    List<Participacao> findByUsuarioSlug(String slug);

    @Query("select p from Participacao p left join fetch p.usuario order by p.id desc")
    List<Participacao> recuperarParticipacoesComUsuario();

    @Query(
            value = "select p from Participacao p left join fetch p.usuario where p.nome like %:nome% order by p.id desc",
            countQuery = "select count(p) from Participacao p where p.nome like %:nome%"
    )
    Page<Participacao>   recuperarParticipacoesPaginadas(String nome, Pageable pageable);

    //atualizar uma participacao
    @Modifying
    @Query(
            value = "update Participacao p set p.nome = :nome where p.id = :id"
    )
    void atualizarParticipacao(@Param("id") Long id, @Param("nome") String nome);

}
