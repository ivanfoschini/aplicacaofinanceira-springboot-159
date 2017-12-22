package aplicacaofinanceira.controller;

import aplicacaofinanceira.exception.NotFoundException;
import aplicacaofinanceira.model.Usuario;
import aplicacaofinanceira.util.LoginJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import aplicacaofinanceira.service.UsuarioService;
import aplicacaofinanceira.util.UsuarioWithTokenSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.security.NoSuchAlgorithmException;

@RestController
public class LoginController extends BaseController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @RequestMapping(
            value = "/api/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody LoginJson loginJson) throws JsonProcessingException, NotFoundException, NoSuchAlgorithmException {
        Usuario usuario = usuarioService.findByNomeDeUsuarioAndSenha(loginJson.getNomeDeUsuario(), loginJson.getSenha());
        
        usuarioService.setTokenEUltimoAcesso(usuario);
        
        return new ResponseEntity<>(UsuarioWithTokenSerializer.serializeUsuarioWithToken(usuario), HttpStatus.OK);
    }    
}
