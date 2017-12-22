package aplicacaofinanceira.service;

import aplicacaofinanceira.exception.NotFoundException;
import aplicacaofinanceira.model.Papel;
import aplicacaofinanceira.model.Servico;
import aplicacaofinanceira.model.Usuario;
import aplicacaofinanceira.repository.ServicoRepository;
import aplicacaofinanceira.repository.UsuarioRepository;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {    

    private static final Integer TOKEN_LENGTH = 32;
    private static final String NUMBERS_AND_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private ServicoRepository servicoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private MessageSource messageSource;
    
    @Override
    public boolean autorizar(String requestUri, String username, String token) {
        System.out.println(requestUri);
        
        Usuario usuario = usuarioRepository.findByNomeDeUsuarioAndToken(username, token);
        Servico servico = servicoRepository.findByUri(requestUri);
        
        if (usuario != null && servico != null) {
            for (Papel papelDoUsuario: usuario.getPapeis()) {
                for (Papel papelDoServico: usuario.getPapeis()) {
                    if (papelDoUsuario.getNome().equals(papelDoServico.getNome())) {
                        usuario.setUltimoAcesso(new LocalDate());
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    @Override
    public Usuario findByNomeDeUsuarioAndSenha(String nomeDeUsuario, String senha) throws NotFoundException, NoSuchAlgorithmException {
        if (senha == null) {
            throw new NotFoundException(messageSource.getMessage("usuarioNaoEncontrado", null, null));
        }
        
        Usuario usuario = usuarioRepository.findByNomeDeUsuarioAndSenha(nomeDeUsuario, generateMD5(senha));

        if (usuario == null) {
            throw new NotFoundException(messageSource.getMessage("usuarioNaoEncontrado", null, null));
        }        
        
        return usuario;
    }    

    @Override
    public void setTokenEUltimoAcesso(Usuario usuario) {
        String token = generateRandonToken();
        
        usuario.setToken(token);
        usuario.setUltimoAcesso(new LocalDate());
        
        usuarioRepository.save(usuario);
    }
    
    private String generateMD5(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(text.getBytes(), 0, text.length());
        
        return new BigInteger(1, messageDigest.digest()).toString(16);
    }
    
    private String generateRandonToken() {
       StringBuilder stringBuilder = new StringBuilder(TOKEN_LENGTH);
       
       for (int i = 0; i < TOKEN_LENGTH; i++) { 
          stringBuilder.append(NUMBERS_AND_CHARACTERS.charAt(secureRandom.nextInt(NUMBERS_AND_CHARACTERS.length())));
       }
       
       return stringBuilder.toString();
    }
}
