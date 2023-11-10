package fernando.servico;

import fernando.modelo.Participacao;
import fernando.excecao.EntidadeNaoEncontradaException;
import fernando.repositorio.ParticipacaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipacaoServico {

    private final ParticipacaoRepositorio participacaoRepositorio;

    @Autowired
    public ParticipacaoServico(ParticipacaoRepositorio participacaoRepositorio) {
        this.participacaoRepositorio = participacaoRepositorio;
    }

    public Participacao atualizaParticipacao(Participacao participacao) {
        return participacaoRepositorio.save(participacao);
    }

    public Participacao cadastraParticipacao(Participacao participacao) {
        return participacaoRepositorio.save(participacao);
    }

    public void removeParticipacao(Long id) {
        recuperaParticipacoesPorId(id);
        participacaoRepositorio.deleteById(id);
    }

    public List<Participacao> recuperaParticipacoes() {
        return participacaoRepositorio.findAll(Sort.by("id"));
    }

    public Participacao recuperaParticipacoesPorId(Long id) {
        return participacaoRepositorio.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Participacao de ID " + id + " não encontrado.")
        );
    }

    public List<Participacao> recuperaParticipacoesPorUsuarioID(Long id){
        return participacaoRepositorio.findByUsuarioId(id);
    }
}