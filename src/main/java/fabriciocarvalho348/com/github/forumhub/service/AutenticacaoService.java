package fabriciocarvalho348.com.github.forumhub.service;

import fabriciocarvalho348.com.github.forumhub.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository UsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Procurando o usuário.
        UserDetails user = UsuarioRepository.login(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        return user;
    }
}