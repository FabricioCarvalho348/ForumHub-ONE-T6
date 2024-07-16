package fabriciocarvalho348.com.github.forumhub.model.repository;

import fabriciocarvalho348.com.github.forumhub.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails login(String login);
}
