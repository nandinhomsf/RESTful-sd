package fernando.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private Integer xp;
    @Column
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
    public Usuario(String nome, int xp, int creditos,  List<Participacao> participacoes){
        this.nome = nome;
        this.xp = xp;
        this.creditos = creditos;
        this.participacoes=participacoes;
    }

}