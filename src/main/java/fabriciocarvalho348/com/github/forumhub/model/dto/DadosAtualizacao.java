package fabriciocarvalho348.com.github.forumhub.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacao(

        @NotNull
        Long id,
        String emailAutor,

        @NotBlank
        String mensagem

) {

}

