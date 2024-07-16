package fabriciocarvalho348.com.github.forumhub.model.dto;

import fabriciocarvalho348.com.github.forumhub.model.entity.Topico;

import java.time.LocalDate;

public record DadosListaTopico(String titulo, String mensagem, String nomeAutor, LocalDate dataDeCriacao) {

    public DadosListaTopico (Topico topico){
        this(   topico.getTitulo(),
                topico.getMensagem(),
                topico.getNomeAutor(),
                topico.getDataDeCriacao()
        );
    }
}