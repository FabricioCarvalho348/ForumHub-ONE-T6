package fabriciocarvalho348.com.github.forumhub.controller;

import fabriciocarvalho348.com.github.forumhub.model.dto.DadosAutenticacao;
import fabriciocarvalho348.com.github.forumhub.model.dto.DadosToken;
import fabriciocarvalho348.com.github.forumhub.model.entity.Usuario;
import fabriciocarvalho348.com.github.forumhub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {

        var autenticacaoToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        var autenticacao = manager.authenticate(autenticacaoToken);

        var tokenGerado = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new DadosToken(tokenGerado));
    }
}