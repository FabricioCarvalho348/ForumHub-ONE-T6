package fabriciocarvalho348.com.github.forumhub.controller;

import fabriciocarvalho348.com.github.forumhub.model.dto.DadosAtualizacao;
import fabriciocarvalho348.com.github.forumhub.model.dto.DadosDetalhesTopico;
import fabriciocarvalho348.com.github.forumhub.model.dto.DadosListaTopico;
import fabriciocarvalho348.com.github.forumhub.model.dto.DadosTopico;
import fabriciocarvalho348.com.github.forumhub.model.entity.Topico;
import fabriciocarvalho348.com.github.forumhub.model.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping ("/topicos")

public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    @PostMapping

    public ResponseEntity registrartopico(@RequestBody @Valid DadosTopico dados, UriComponentsBuilder uriBuilder ) {

        Optional<Topico> topicoExistente = repository.findByTituloAndNomeAutor(dados.titulo(), dados.nomeAutor());
        if (topicoExistente.isPresent()) {
            return new ResponseEntity<> (new DadosDetalhesTopico(topicoExistente.get(), "Tópico já existente."), HttpStatus.BAD_REQUEST);
        }

        var topico = new Topico(dados);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhesTopico(topico, "Tópico criado com sucesso!"));
    }

    @GetMapping
    public Page <DadosListaTopico> listarTopicos(
            @PageableDefault(size = 10, sort = {"dataDeCriacao"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListaTopico::new);
    }


    @GetMapping("/{id}")
    public ResponseEntity <DadosDetalhesTopico> detalharTopico (@PathVariable Long id) {
        Optional<Topico> topicoOptional = repository.findById(id);
        if (topicoOptional.isPresent()) {
            var topico = topicoOptional.get();
            return ResponseEntity.ok(new DadosDetalhesTopico (topico, "Detalhes do tópico"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacao dados) {
        Optional<Topico> topicoOptional = repository.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.atualizarInformacoes(dados);
            repository.save(topico);
            return new ResponseEntity<>("Tópico atualizado com sucesso!", HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Topico> topicoOptional = repository.findById(id);
        if (topicoOptional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
