package fernando;

import fernando.modelo.Participacao;
import fernando.modelo.Usuario;
import fernando.repositorio.ParticipacaoRepositorio;
import fernando.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RESTfulAPI implements CommandLineRunner {

    private final UsuarioRepositorio usuarioRepositorio;

    private final ParticipacaoRepositorio participacaoRepositorio;

    public RESTfulAPI(UsuarioRepositorio usuarioRepositorio, ParticipacaoRepositorio participacaoRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.participacaoRepositorio = participacaoRepositorio;
    }

    public static void main(String[] args) {
        SpringApplication.run(RESTfulAPI.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        participacaoRepositorio.deleteAll();
        usuarioRepositorio.deleteAll();


        Usuario usuario1 = new Usuario("Renato", 4,3);
        usuarioRepositorio.save(usuario1);

        Usuario usuario2 = new Usuario("Fernando", 39,53);
        usuarioRepositorio.save(usuario2);


        Participacao participacao1 = new Participacao("Yorick", usuario1);
        participacaoRepositorio.save(participacao1);

        Participacao participacao2 = new Participacao("Draven" , usuario2);
        participacaoRepositorio.save(participacao2);

        Participacao participacao3 = new Participacao("Fiora",  usuario1);
        participacaoRepositorio.save(participacao3);

    }
}