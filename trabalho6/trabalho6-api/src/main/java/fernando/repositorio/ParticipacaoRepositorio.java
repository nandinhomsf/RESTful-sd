package fernando.repositorio;

import fernando.modelo.Participacao;
import fernando.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipacaoRepositorio extends JpaRepository<Participacao, Long> {
    @Query("SELECT p FROM Participacao p WHERE p.usuario.id = :id")
    List<Participacao> findByUsuarioId(Long id);
}
