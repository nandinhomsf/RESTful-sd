package fernando.controle;


import fernando.modelo.Participacao;
import fernando.modelo.Usuario;
import org.springframework.web.bind.annotation.*;
import fernando.servico.ParticipacaoServico;

import java.util.List;

@RestController
@RequestMapping("participacoes/")
public class ParticipacaoControle {

    private final ParticipacaoServico participacaoServico;

    public ParticipacaoControle(ParticipacaoServico participacaoServico) {
        this.participacaoServico = participacaoServico;
    }

    @PutMapping
    public Participacao atualizarParticipacao(@RequestBody Participacao participacao) {
        return participacaoServico.atualizaParticipacao(participacao);
    }

    @PostMapping
    public Participacao cadastraParticipacao(@RequestBody Participacao participacao) {
        return participacaoServico.cadastraParticipacao(participacao);
    }

    @DeleteMapping("{idParticipacao}")
    public void removeParticipacao(@PathVariable("idParticipacao") Long id) {
        participacaoServico.removeParticipacao(id);
    }

    @GetMapping
    public List<Participacao> recuperaParticipacoes() {
        return participacaoServico.recuperaParticipacoes();
    }

    @GetMapping("{idParticipacao}")
    private Participacao recuperaParticipacaoPorId(@PathVariable("idParticipacao") Long id) {
        return participacaoServico.recuperaParticipacoesPorId(id);
    }

    @GetMapping("usuario/{idUsuario}")
    public List<Participacao> recuperaParticipacaoPorUsuario(@PathVariable("idUsuario") Long id) {
        return participacaoServico.recuperaParticipacoesPorUsuarioID(id);
    }
}