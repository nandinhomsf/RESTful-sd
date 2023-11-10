package fernando.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "xp")
    private Integer xp;
    @Column(name = "creditos")
    private Integer creditos;
    @JsonIgnoreProperties("usuario")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Participacao> participacoes;

    public void addParticipacoes(Participacao participacao) {
        if (this.participacoes == null) {
            this.participacoes = new ArrayList<>();
        }
        this.participacoes.add(participacao);
    }
    public Usuario(String nome, int xp, int creditos){
        this.nome = nome;
        this.xp = xp;
        this.creditos = creditos;
    }

}