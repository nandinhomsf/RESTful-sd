package fernando.controle;

import fernando.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import fernando.servico.UsuarioServico;

import java.util.List;

@RestController
@RequestMapping(path ="usuarios/")
public class UsuarioControle {

    private final UsuarioServico usuarioServico;

    @Autowired
    public UsuarioControle(UsuarioServico usuarioServico) {
        this.usuarioServico = usuarioServico;
    }

    @GetMapping
    public List<Usuario> recuperaUsuarios() {
        return usuarioServico.recuperaUsuarios();
    }

    @PostMapping
    public Usuario cadastraUsuario(@RequestBody Usuario usuario) {
        return usuarioServico.cadastraUsuario(usuario);
    }

    @GetMapping("{idUsuario}")
    public Usuario recuperaUsuarioPorId(@PathVariable("idUsuario") Long id) {
        return usuarioServico.recuperaUsuarioPorId(id);
    }

    @PutMapping
    public Usuario atualizaUsuario(@RequestBody Usuario usuario) {
        return usuarioServico.atualizaUsuario(usuario);
    }

    @DeleteMapping("{idUsuario}")
    public void removeUsuario(@PathVariable("idUsuario") Long id) {
        usuarioServico.removeUsuario(id);
    }

    @GetMapping("participacao/{idParticipacao}")
    public List<Usuario> recuperaUsuarioPorParticipacao(@PathVariable("idParticipacao") Long id) {
        return usuarioServico.recuperaUsuarioPorParticipacao(id);
    }

}