package aplicacaofinanceira.controller;

import aplicacaofinanceira.exception.NotFoundException;
import aplicacaofinanceira.model.Usuario;
import aplicacaofinanceira.service.UsuarioService;
import aplicacaofinanceira.util.LogoutJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController extends BaseController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @RequestMapping(
            value = "/logout/logout",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> logout(@RequestBody LogoutJson logoutJson) throws JsonProcessingException, NotFoundException {
        Usuario usuario = usuarioService.findByNomeDeUsuario(logoutJson.getNomeDeUsuario());
        
        usuarioService.nullifyTokenEUltimoAcesso(usuario);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
