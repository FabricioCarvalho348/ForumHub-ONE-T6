package fabriciocarvalho348.com.github.forumhub.model.repository;

import fabriciocarvalho348.com.github.forumhub.model.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndNomeAutor(String titulo, String nomeAutor);

}
