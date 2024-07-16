package fabriciocarvalho348.com.github.forumhub.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;


public record DadosTopico(

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,
        LocalDate dataDeCriacao,

        @NotBlank
        String nomeAutor,

        @NotBlank
        @Email
        String emailAutor

) {

}