package fabriciocarvalho348.com.github.forumhub.model.dto;

import fabriciocarvalho348.com.github.forumhub.model.entity.Topico;

public record DadosDetalhesTopico(Topico topico, String titulo, String mensagem, String nomeAutor, String dataDeCriacao) {

    public DadosDetalhesTopico(Topico topico, String extraInfo) {
        this(topico, topico.getTitulo(), topico.getMensagem(), topico.getNomeAutor(), topico.getDataDeCriacao().toString());
    }
}