package aplicacaofinanceira.controller;

import aplicacaofinanceira.exception.NotEmptyCollectionException;
import aplicacaofinanceira.exception.NotFoundException;
import aplicacaofinanceira.exception.NotUniqueException;
import aplicacaofinanceira.exception.ValidationException;
import aplicacaofinanceira.model.Banco;
import aplicacaofinanceira.service.BancoService;
import aplicacaofinanceira.util.BancoViews;
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
public class BancoController extends BaseController {

    @Autowired
    private BancoService bancoService;

    /**
     * @api {delete} /banco/delete/:id Delete
     * @apiGroup Banco
     * @apiVersion 1.0.0
     *
     * @apiHeader {String} nomeDeUsuario Nome do usuário que está realizando a requisição.
     * @apiHeader {String} token         Token de acesso.
     *
     * @apiHeaderExample {String} Exemplo de cabeçalho:  
     *     "nomeDeUsuario": admin
     *     "token":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0
     *
     * @apiParam {Number} id Identificador do banco.
     *
     * @apiSuccessExample Resposta de sucesso
     *     HTTP/1.1 204 No Content
     *
     * @apiError Unauthorized Usuário não autorizado a acessar o recurso solicitado.
     *
     * @apiErrorExample Unauthorized
     *     HTTP/1.1 401 Unauthorized     
     *
     * @apiError NotFound Não existe um banco que possua o identificador passado como parâmetro.
     *
     * @apiErrorExample NotFound
     *     HTTP/1.1 404 Not Found
     *     {
     *         "timestamp": "09/06/2018 09:34:35",
     *         "status": 404,
     *         "reason": "Not Found",
     *         "exception": "aplicacaofinanceira.exception.NotFoundException",
     *         "message": "Banco não encontrado",
     *         "path": "/banco/delete/0"
     *     }       
     *
     * @apiError UnprocessableEntity O banco selecionado possui agências e, portanto, não pode ser excluído.
     *
     * @apiErrorExample UnprocessableEntity
     *     HTTP/1.1 422 Unprocessable Entity
     *     {
     *         "timestamp": "09/06/2018 09:37:36",
     *         "status": 422,
     *         "reason": "Unprocessable Entity",
     *         "exception": "aplicacaofinanceira.exception.NotEmptyCollectionException",
     *         "message": "O banco selecionado possui pelo menos uma agência e, portanto, não pode ser excluído",
     *         "path": "/banco/delete/1"
     *     }
     */
    @RequestMapping(
            value = "/banco/delete/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws NotEmptyCollectionException, NotFoundException {
        bancoService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * @api {get} /banco/list List
     * @apiGroup Banco
     * @apiVersion 1.0.0
     *
     * @apiHeader {String} nomeDeUsuario Nome do usuário que está realizando a requisição.
     * @apiHeader {String} token         Token de acesso.
     *
     * @apiHeaderExample {String} Exemplo de cabeçalho:  
     *     "nomeDeUsuario": admin
     *     "token":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0
     *
     * @apiSuccess {Number} id        Identificador do banco.
     * @apiSuccess {Number} numero    Numero do banco.
     * @apiSuccess {String} cnpj      CNPJ do banco.
     * @apiSuccess {String} nome      Nome do banco.
     *
     * @apiSuccessExample Resposta de sucesso
     *     HTTP/1.1 200 OK
     *     [
     *       {
     *         "id": 1,
     *         "numero": 1,
     *         "cnpj": "00000000000191",
     *         "nome": "Banco do Brasil"
     *       },
     *       {
     *         "id": 2,
     *         "numero": 2,
     *         "cnpj": "00360305000104",
     *         "nome": "Caixa Econômica Federal"
     *       },
     *       {
     *         "id": 3,
     *         "numero": 3,
     *         "cnpj": "60872504000123",
     *         "nome": "Itaú"
     *       }
     *     ] 
     *
     * @apiError Unauthorized Usuário não autorizado a acessar o recurso solicitado.
     *
     * @apiErrorExample Unauthorized
     *     HTTP/1.1 401 Unauthorized
     */
    @RequestMapping(
            value = "/banco/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(BancoViews.BancoSimple.class)
    public ResponseEntity<List<Banco>> findAll() {
        List<Banco> bancos = bancoService.findAll();

        return new ResponseEntity<>(bancos, HttpStatus.OK);
    }

    /**
     * @api {get} /banco/show/:id Show
     * @apiGroup Banco
     * @apiVersion 1.0.0
     *
     * @apiHeader {String} nomeDeUsuario Nome do usuário que está realizando a requisição.
     * @apiHeader {String} token         Token de acesso.
     *
     * @apiHeaderExample {String} Exemplo de cabeçalho:  
     *     "nomeDeUsuario": admin
     *     "token":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0
     *
     * @apiParam {Number} id Identificador do banco.
     *
     * @apiSuccess {Number} id        Identificador do banco.
     * @apiSuccess {Number} numero    Numero do banco.
     * @apiSuccess {String} cnpj      CNPJ do banco.
     * @apiSuccess {String} nome      Nome do banco.
     *
     * @apiSuccessExample Resposta de sucesso
     *     HTTP/1.1 200 OK
     *     {
     *       "id": 1,
     *       "numero": 1,
     *       "cnpj": "00000000000191",
     *       "nome": "Banco do Brasil"
     *     } 
     *
     * @apiError Unauthorized Usuário não autorizado a acessar o recurso solicitado.
     *
     * @apiErrorExample Unauthorized
     *     HTTP/1.1 401 Unauthorized          
     *
     * @apiError NotFound Não existe um banco que possua o identificador passado como parâmetro.
     *
     * @apiErrorExample NotFound
     *     HTTP/1.1 404 Not Found
     *     {
     *       "timestamp": "09/06/2018 10:02:47",
     *       "status": 404,
     *       "reason": "Not Found",
     *       "exception": "aplicacaofinanceira.exception.NotFoundException",
     *       "message": "Banco não encontrado",
     *       "path": "/banco/show/0"
     *     }      
     */
    @RequestMapping(
            value = "/banco/show/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(BancoViews.BancoSimple.class)
    public ResponseEntity<Banco> findOne(@PathVariable("id") Long id) throws NotFoundException {        
        Banco banco = bancoService.findOne(id);

        return new ResponseEntity<>(banco, HttpStatus.OK);        
    }

    /**
     * @api {post} /banco/save Save
     * @apiGroup Banco
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
     *       "numero": 1, 
     *       "cnpj": "00000000000191",   
     *       "nome": "Banco do Brasil"   
     *     }
     *
     * @apiSuccess {Number} id      Identificador do banco.
     * @apiSuccess {Number} numero  Numero do banco.
     * @apiSuccess {String} cnpj    CNPJ do banco.
     * @apiSuccess {String} nome    Nome do banco.
     *
     * @apiSuccessExample Resposta de sucesso
     *     HTTP/1.1 201 Created
     *     {
     *       "id": 1,
     *       "numero": 1,
     *       "cnpj": "00000000000191",
     *       "nome": "Banco do Brasil"
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
     *       "timestamp": "09/06/2018 09:48:18",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O CNPJ fornecido não pode ser nulo",
     *         "O CNPJ fornecido é inválido",
     *         "O nome fornecido não pode ser nulo",
     *         "O número fornecido deve ser maior do que zero"
     *       ],
     *       "path": "/banco/save"
     *     }
     *
     *     HTTP/1.1 422 UnprocessableEntity - "O número fornecido é menor do que zero."
     *
     *     {
     *       "timestamp": "09/06/2018 09:53:33",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O número fornecido deve ser maior do que zero"
     *       ],
     *       "path": "/banco/save"
     *     }
     *
     *     HTTP/1.1 422 UnprocessableEntity - "O número fornecido já pertence a outro banco."
     *
     *     {
     *       "timestamp": "09/06/2018 09:55:00",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.NotUniqueException",
     *       "message": "O número fornecido já pertence a outro banco",
     *       "path": "/banco/save"
     *     }
     *     
     *     HTTP/1.1 422 UnprocessableEntity - "O CNPJ fornecido é inválido."
     *
     *     {
     *       "timestamp": "09/06/2018 09:55:56",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O CNPJ fornecido é inválido"
     *       ],
     *       "path": "/banco/save"
     *     }
     *     
     *     HTTP/1.1 422 UnprocessableEntity - "O nome do banco deve ter entre 2 e 255 caracteres."
     *
     *     {
     *       "timestamp": "09/06/2018 09:57:25",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O nome do banco deve ter entre 2 e 255 caracteres"
     *       ],
     *       "path": "/banco/save"
     *     }     
     */
    @RequestMapping(
            value = "banco/save",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(BancoViews.BancoSimple.class)
    public ResponseEntity<Banco> insert(@RequestBody @Valid Banco banco, BindingResult bindingResult) throws NotUniqueException, ValidationException  {
        if (bindingResult.hasErrors()) {
            ValidationUtil.handleValidationErrors(bindingResult);
            
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {            
            Banco savedBanco = bancoService.insert(banco);

            return new ResponseEntity<>(savedBanco, HttpStatus.CREATED);            
        }
    }

    /**
     * @api {put} /banco/update/:id Update
     * @apiGroup Banco
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
     * @apiParam {Number} id Identificador do banco.
     *
     * @apiParamExample {json} Exemplo de requisição
     *     {
     *       "numero": 1, 
     *       "cnpj": "00000000000191",   
     *       "nome": "Banco do Brasil"   
     *     }
     *
     * @apiSuccess {Number} id        Identificador do banco.
     * @apiSuccess {Number} numero    Numero do banco.
     * @apiSuccess {String} cnpj      CNPJ do banco.
     * @apiSuccess {String} nome      Nome do banco.
     *
     * @apiSuccessExample Resposta de sucesso
     *     HTTP/1.1 200 OK
     *     {
     *       "id": 1,
     *       "numero": 1,
     *       "cnpj": "00000000000191",
     *       "nome": "Banco do Brasil"
     *     } 
     *
     * @apiError Unauthorized Usuário não autorizado a acessar o recurso solicitado.
     *
     * @apiErrorExample Unauthorized
     *     HTTP/1.1 401 Unauthorized     
     *
     * @apiError NotFound Não existe um banco que possua o identificador passado como parâmetro.
     *
     * @apiErrorExample NotFound
     *     HTTP/1.1 404 Not Found
     *     {
     *       "timestamp": "09/06/2018 10:07:04",
     *       "status": 404,
     *       "reason": "Not Found",
     *       "exception": "aplicacaofinanceira.exception.NotFoundException",
     *       "message": "Banco não encontrado",
     *       "path": "/banco/update/0"
     *     }
     *
     * @apiError UnprocessableEntity Erros de validação de dados ou violação de regras de negócio.
     * 
     * @apiErrorExample UnprocessableEntity
     *     HTTP/1.1 422 UnprocessableEntity - "Campos obrigatórios não foram fornecidos."
     *     
     *     {
     *       "timestamp": "09/06/2018 10:07:47",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O CNPJ fornecido não pode ser nulo",
     *         "O CNPJ fornecido é inválido",
     *         "O nome fornecido não pode ser nulo",
     *         "O número fornecido deve ser maior do que zero"
     *       ],
     *       "path": "/banco/update/1"
     *     }
     *
     *     HTTP/1.1 422 UnprocessableEntity - "O número fornecido é menor do que zero."
     *
     *     {
     *       "timestamp": "09/06/2018 10:09:06",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O número fornecido deve ser maior do que zero"
     *       ],
     *       "path": "/banco/update/1"
     *     }
     *
     *     HTTP/1.1 422 UnprocessableEntity - "O número fornecido já pertence a outro banco."
     *
     *     {
     *       "timestamp": "09/06/2018 10:10:02",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.NotUniqueException",
     *       "message": "O número fornecido já pertence a outro banco",
     *       "path": "/banco/update/1"
     *     }
     *     
     *     HTTP/1.1 422 UnprocessableEntity - "O CNPJ fornecido é inválido."
     *
     *     {
     *       "timestamp": "09/06/2018 10:11:03",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O CNPJ fornecido é inválido"
     *       ],
     *       "path": "/banco/update/1"
     *     }
     *     
     *     HTTP/1.1 422 UnprocessableEntity - "O nome do banco deve ter entre 2 e 255 caracteres."
     *
     *     {
     *       "timestamp": "09/06/2018 10:12:11",
     *       "status": 422,
     *       "reason": "Unprocessable Entity",
     *       "exception": "aplicacaofinanceira.exception.ValidationException",
     *       "messages": [
     *         "O nome do banco deve ter entre 2 e 255 caracteres"
     *       ],
     *       "path": "/banco/update/1"
     *     }     
     */
    @RequestMapping(
            value = "/banco/update/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(BancoViews.BancoSimple.class)
    public ResponseEntity<Banco> update(@PathVariable("id") Long id, @RequestBody @Valid Banco banco, BindingResult bindingResult) throws NotFoundException, NotUniqueException, ValidationException {
        if (bindingResult.hasErrors()) {
            ValidationUtil.handleValidationErrors(bindingResult);
            
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            Banco updatedBanco = bancoService.update(id, banco);

            return new ResponseEntity<>(updatedBanco, HttpStatus.OK);
        }
    }
}
