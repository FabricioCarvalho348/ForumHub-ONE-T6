package fabriciocarvalho348.com.github.forumhub.model.entity;

import fabriciocarvalho348.com.github.forumhub.model.dto.DadosAtualizacao;
import fabriciocarvalho348.com.github.forumhub.model.dto.DadosTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    @Column(name = "dataDeCriacao")
    private LocalDate dataDeCriacao = LocalDate.now();

    private String nomeAutor;

    public Topico(DadosTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataDeCriacao = (dados.dataDeCriacao() != null) ? dados.dataDeCriacao() : LocalDate.now();
        this.nomeAutor = dados.nomeAutor();
    }

    public void atualizarInformacoes(DadosAtualizacao dados) {
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
    }

}
