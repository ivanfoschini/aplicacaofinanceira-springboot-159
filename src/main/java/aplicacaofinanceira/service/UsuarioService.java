package aplicacaofinanceira.service;

import aplicacaofinanceira.exception.NotFoundException;
import aplicacaofinanceira.model.Usuario;
import java.security.NoSuchAlgorithmException;

public interface UsuarioService {
    
    Usuario findByNomeDeUsuarioAndSenha(String nomeDeUsuario, String senha) throws NotFoundException, NoSuchAlgorithmException;

    void setTokenEUltimoAcesso(Usuario usuario);
}
