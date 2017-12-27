package aplicacaofinanceira.controller;

import aplicacaofinanceira.exception.NotEmptyCollectionException;
import aplicacaofinanceira.exception.NotFoundException;
import aplicacaofinanceira.exception.NotUniqueException;
import aplicacaofinanceira.exception.ValidationException;
import aplicacaofinanceira.model.Estado;
import aplicacaofinanceira.service.EstadoService;
import aplicacaofinanceira.util.EstadoViews;
import aplicacaofinanceira.validation.ValidationUtil;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstadoController extends BaseController {

    @Autowired
    private EstadoService estadoService;

    /**
     * @api {delete} /estado/delete/:id Delete
     * @apiGroup Estado
     * @apiVersion 1.0.0
     *
     * @apiHeader {String} nomeDeUsuario Nome do usuário que está realizando a requisição.
     * @apiHeader {String} token         Token de acesso.
     *
     * @apiHeaderExample {String} Exemplo de cabeçalho:  
     *     "nomeDeUsuario": admin
     *     "token":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0
     *
     * @apiParam {Number} id Identificador do estado.
     *
     * @apiSuccessExample Resposta de sucesso
     *     HTTP/1.1 204 No Content
     *
     * @apiError Unauthorized Usuário não autorizado a acessar o recurso solicitado.
     *
     * @apiErrorExample Unauthorized
     *     HTTP/1.1 401 Unauthorized
     *
     * @apiError NotFound Não existe um estado que possua o identificador passado como parâmetro.
     *
     * @apiErrorExample NotFound
     *     {
     *       "timestamp": "09/06/2018 10:17:58",
     *       "status": 404,
     *       "reason": "Not Found",
     *       "exception": "aplicacaofinanceira.exception.NotFoundException",
     *       "message": "Estado não encontrado",
     *       "path": "/estado/delete/0"
     *     }      
     *
     * @apiError UnprocessableEntity O estado selecionado possui cidades e, portanto, não pode ser excluído.
     *
     * @apiErrorExample UnprocessableEntity
     *     HTTP/1.1 422 Unprocessable Entity
     *     {
     *         "timestamp": "09/06/2018 09:37:36",
     *         "status": 422,
     *         "reason": "Unprocessable Entity",
     *         "exception": "aplicacaofinanceira.exception.NotEmptyCollectionException",
     *         "message": "O estado selecionado possui pelo menos uma cidade e, portanto, não pode ser excluído",
     *         "path": "/banco/delete/1"
     *     }
     */
    @RequestMapping(
            value = "/estado/delete/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws NotEmptyCollectionException, NotFoundException {
        estadoService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * @api {get} /estado/list List
     * @apiGroup Estado
     * @apiVersion 1.0.0
     *
     * @apiHeader {String} nomeDeUsuario Nome do usuário que está realizando a requisição.
     * @apiHeader {String} token         Token de acesso.
     *
     * @apiHeaderExample {String} Exemplo de cabeçalho:  
     *     "nomeDeUsuario": admin
     *     "token":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0
     *
     * @apiSuccess {Number} id        Identificador do estado.
     * @apiSuccess {String} nome      Nome do estado.
     *
     * @apiSuccessExample Resposta de sucesso
     *     HTTP/1.1 200 OK
     *     [
     *       {
     *         "id": 3,
     *         "nome": "Espirito Santo"
     *       },
     *       {
     *         "id": 2,
     *         "nome": "Rio de Janeiro"
     *       },
     *       {
     *         "id": 1,
     *         "nome": "Sao Paulo"
     *       }
     *     ] 
     *
     * @apiError Unauthorized Usuário não autorizado a acessar o recurso solicitado.
     *
     * @apiErrorExample Unauthorized
     *     HTTP/1.1 401 Unauthorized     
     *
     */
    @RequestMapping(
            value = "/estado/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(EstadoViews.EstadoSimple.class)
    public ResponseEntity<List<Estado>> findAll() {
        List<Estado> estados = estadoService.findAll();

        return new ResponseEntity<>(estados, HttpStatus.OK);
    }

    /**
     * @api {get} /estado/show/:id Show
     * @apiGroup Estado
     * @apiVersion 1.0.0
     *
     * @apiHeader {String} nomeDeUsuario Nome do usuário que está realizando a requisição.
     * @apiHeader {String} token         Token de acesso.
     *
     * @apiHeaderExample {String} Exemplo de cabeçalho:  
     *     "nomeDeUsuario": admin
     *     "token":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0
     *
     * @apiParam {Number} id Identificador do estado.
     *
     * @apiSuccess {Number} id        Identificador do estado.
     * @apiSuccess {String} nome      Nome do estado.
     *
     * @apiSuccessExample Resposta de sucesso
     *     HTTP/1.1 200 OK
     *     {
     *       "id": 1,
     *       "nome": "Sao Paulo"
     *     } 
     *
     * @apiError Unauthorized Usuário não autorizado a acessar o recurso solicitado.
     *
     * @apiErrorExample Unauthorized
     *     HTTP/1.1 401 Unauthorized     
     *
     * @apiError NotFound Não existe um estado que possua o identificador passado como parâmetro.
     *
     * @apiErrorExample NotFound
     *     HTTP/1.1 404 Not Found
     *     {
     *       "timestamp": "09/06/2018 10:29:21",
     *       "status": 404,
     *       "reason": "Not Found",
     *       "exception": "aplicacaofinanceira.exception.NotFoundException",
     *       "message": "Estado não encontrado",
     *       "path": "/estado/show/0"
     *     }      
     */
    @RequestMapping(
            value = "/estado/show/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(EstadoViews.EstadoSimple.class)
    public ResponseEntity<Estado> findOne(@PathVariable("id") Long id) throws NotFoundException {        
        Estado estado = estadoService.findOne(id);

        return new ResponseEntity<>(estado, HttpStatus.OK);        
    }
    
    /**
     * @api {post} /estado/save Save
     * @apiGroup Estado
     * @apiVersion 1.0.0
     *
     * @apiHeader {String} Content-Type  Tipo do conteúdo da requisição.
     * @apiHeader {String} nomeDeUsuario Nome do usuário que está realizando a requisição.
     * @apiHeader {String} token         Token de acesso.
     *
     * @apiHeaderExample {String} Exemplo de cabeçalho:  
     *     "Content-Type":  application/json
     *     "nomeDeUsuario": admin
     *     "token":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0
     *
     * @apiExample {json} Exemplo de requisição
     *     {
     *       "nome": "Sao Paulo"
     *     }
     *     
     * @apiSuccess {Number} id      Identificador do estado.
     * @apiSuccess {String} nome    Nome do estado.
     *
     * @apiSuccessExample Resposta de sucesso
     *     HTTP/1.1 201 Created
     *     {
     *       "id": 1,
     *       "nome": "Sao Paulo"
     *     } 
     *
     * @apiError Unauthorized Usuário não autorizado a acessar o recurso solicitado.
     *
     * @apiErrorExample Unauthorized
     *     HTTP/1.1 401 Unauthorized
     *
     * @apiError UnprocessableEntity Erros de validação de dados ou violação de regras de negócio.
     * 
     * @apiErrorExample UnprocessableEntity
     *     HTTP/1.1 422 UnprocessableEntity - "Campos obrigatórios não foram fornecidos."
     *     
     *     {
     *       "timestamp": "09/06/2018 10:23:34",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O nome fornecido não pode ser nulo"
     *       ],
     *       "path": "/estado/save"
     *     }
     *
     *     HTTP/1.1 422 UnprocessableEntity - "O nome fornecido já pertence a outro estado."
     *
     *     {
     *       "timestamp": "09/06/2018 10:24:35",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.NotUniqueException",
     *       "message": "O nome fornecido já pertence a outro estado",
     *       "path": "/estado/save"
     *     }
     *
     *     HTTP/1.1 422 UnprocessableEntity - "O nome do estado deve ter entre 2 e 255 caracteres."
     *
     *     {
     *       "timestamp": "09/06/2018 10:25:27",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O nome do estado deve ter entre 2 e 255 caracteres"
     *       ],
     *       "path": "/estado/save"
     *     }
     */
    @RequestMapping(
            value = "/estado/save",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(EstadoViews.EstadoSimple.class)
    public ResponseEntity<Estado> insert(@RequestBody @Valid Estado estado, BindingResult bindingResult) throws NotUniqueException, ValidationException  {
        if (bindingResult.hasErrors()) {
            ValidationUtil.handleValidationErrors(bindingResult);
            
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {            
            Estado savedEstado = estadoService.insert(estado);

            return new ResponseEntity<>(savedEstado, HttpStatus.CREATED);            
        }
    }

    /**
     * @api {put} /estado/update/:id Update
     * @apiGroup Estado
     * @apiVersion 1.0.0
     *
     * @apiHeader {String} Content-Type  Tipo do conteúdo da requisição.
     * @apiHeader {String} nomeDeUsuario Nome do usuário que está realizando a requisição.
     * @apiHeader {String} token         Token de acesso.
     *
     * @apiHeaderExample {String} Exemplo de cabeçalho:  
     *     "Content-Type":  application/json
     *     "nomeDeUsuario": admin
     *     "token":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0
     *
     * @apiParam {Number} id Identificador do estado.
     *
     * @apiParamExample {json} Exemplo de requisição
     *     {
     *       "nome": "Sao Paulo"   
     *     }
     *
     * @apiSuccess {Number} id      Identificador do estado.
     * @apiSuccess {String} nome    Nome do estado.
     *
     * @apiSuccessExample Resposta de sucesso
     *     HTTP/1.1 200 OK
     *     {
     *       "id": 1,
     *       "nome": "Sao Paulo"
     *     } 
     *
     * @apiError Unauthorized Usuário não autorizado a acessar o recurso solicitado.
     *
     * @apiErrorExample Unauthorized
     *     HTTP/1.1 401 Unauthorized
     *
     * @apiError NotFound Não existe um estado que possua o identificador passado como parâmetro.
     *
     * @apiErrorExample NotFound
     *     HTTP/1.1 404 Not Found
     *     {
     *       "timestamp": "09/06/2018 10:32:21",
     *       "status": 404,
     *       "reason": "Not Found",
     *       "exception": "aplicacaofinanceira.exception.NotFoundException",
     *       "message": "Estado não encontrado",
     *       "path": "/estado/update/0"
     *     }
     *
     * @apiError UnprocessableEntity Erros de validação de dados ou violação de regras de negócio.
     * 
     * @apiErrorExample UnprocessableEntity
     *     HTTP/1.1 422 UnprocessableEntity - "Campos obrigatórios não foram fornecidos."
     *     
     *     {
     *       "timestamp": "09/06/2018 10:33:16",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O nome fornecido não pode ser nulo"
     *       ],
     *       "path": "/estado/update/1"
     *     }
     *
     *     HTTP/1.1 422 UnprocessableEntity - "O nome fornecido já pertence a outro estado."
     *
     *     {
     *       "timestamp": "09/06/2018 10:10:02",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.NotUniqueException",
     *       "message": "O nome fornecido já pertence a outro estado",
     *       "path": "/estado/update/1"
     *     }
     * 
     *     HTTP/1.1 422 UnprocessableEntity - "O nome do estado deve ter entre 2 e 255 caracteres."     
     * 
     *     {
     *       "timestamp": "09/06/2018 10:35:41",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O nome do estado deve ter entre 2 e 255 caracteres"
     *       ],
     *       "path": "/estado/update/1"
     *     }
     */
    @RequestMapping(
            value = "/estado/update/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(EstadoViews.EstadoSimple.class)
    public ResponseEntity<Estado> update(@PathVariable("id") Long id, @RequestBody @Valid Estado estado, BindingResult bindingResult) throws NotFoundException, NotUniqueException, ValidationException {
        if (bindingResult.hasErrors()) {
            ValidationUtil.handleValidationErrors(bindingResult);
            
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            Estado updatedEstado = estadoService.update(id, estado);

            return new ResponseEntity<>(updatedEstado, HttpStatus.OK);
        }
    }    
}