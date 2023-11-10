package fernando.servico;

import fernando.modelo.Participacao;
import fernando.modelo.Usuario;
import fernando.excecao.EntidadeNaoEncontradaException;
import fernando.repositorio.ParticipacaoRepositorio;
import fernando.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;
    private final ParticipacaoRepositorio participacaoRepositorio;

    @Autowired
    public UsuarioServico(UsuarioRepositorio usuarioRepositorio, ParticipacaoRepositorio participacaoRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.participacaoRepositorio = participacaoRepositorio;
    }

    public List<Usuario> recuperaUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Usuario cadastraUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public Usuario recuperaUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Usuario de ID " + id + " não encontrado."));
    }

    public Usuario atualizaUsuario(Usuario usuario) {
        if(usuario.getParticipacoes()!=null) {
            Usuario umUsuario = recuperaUsuarioPorId(usuario.getId());
            List<Participacao> newParticipacoes = usuario.getParticipacoes();
            List<Participacao> oldParticipacoes = umUsuario.getParticipacoes();

            for (Participacao participacao : newParticipacoes) {
                if (!oldParticipacoes.contains(participacao)) {
                    participacaoRepositorio.findById(participacao.getId()).orElseThrow(() -> new EntidadeNaoEncontradaException(
                            "Participacao de ID " + participacao.getId() + " não encontrado."
                    ));
                }
            }
        }
        return usuarioRepositorio.save(usuario);
    }

    public void removeUsuario(Long id) {
        Usuario usuario = recuperaUsuarioPorId(id);
        if(usuario.getParticipacoes()!=null && usuario.getParticipacoes().size()>0){
            participacaoRepositorio.deleteAll(usuario.getParticipacoes());
        }
        usuarioRepositorio.delete(usuario);
    }

    public List<Usuario> recuperaUsuarioPorParticipacao(Long id) {
        return usuarioRepositorio.findByParticipacaoId(id);
    }
}