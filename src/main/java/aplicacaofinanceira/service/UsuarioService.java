package aplicacaofinanceira.service;

import aplicacaofinanceira.exception.NotFoundException;
import aplicacaofinanceira.model.Usuario;
import java.security.NoSuchAlgorithmException;

public interface UsuarioService {
    
    boolean autorizar(String requestUri, String username, String token);
    
    Usuario findByNomeDeUsuarioAndSenha(String nomeDeUsuario, String senha) throws NotFoundException, NoSuchAlgorithmException;

    void setTokenEUltimoAcesso(Usuario usuario);
}
