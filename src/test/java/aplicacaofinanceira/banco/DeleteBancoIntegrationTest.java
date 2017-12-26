package aplicacaofinanceira.banco;

import aplicacaofinanceira.BaseIntegrationTest;
import aplicacaofinanceira.deserializer.ErrorResponseDeserializer;
import aplicacaofinanceira.model.Banco;
import aplicacaofinanceira.repository.BancoRepository;
import aplicacaofinanceira.util.BancoTestUtil;
import aplicacaofinanceira.util.TestUtil;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class DeleteBancoIntegrationTest extends BaseIntegrationTest {
 
    private String uri = BancoTestUtil.BANCO_DELETE_URI + TestUtil.ID_COMPLEMENT_URI;
    
    @Autowired
    private BancoRepository bancoRepository;
    
    @Autowired
    private MessageSource messageSource;
    
    @Before
    public void setUp() {
        super.setUp();
    }
    
    @Test
    public void testDeleteComUsuarioNaoAutorizado() throws Exception {
        Banco banco = BancoTestUtil.bancoDoBrasil();
        
        bancoRepository.save(banco);
        
        Long id = banco.getId();
        
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete(uri, id)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(TestUtil.NOME_DE_USUARIO, TestUtil.NAO_AUTORIZADO)                
                        .header(TestUtil.TOKEN, TestUtil.ADMIN_TOKEN))                 
                .andReturn();

        int status = result.getResponse().getStatus();
        
        Assert.assertEquals(HttpStatus.UNAUTHORIZED.value(), status);
    }
    
    @Test
    public void testDeleteComUsuarioComCredenciaisIncorretas() throws Exception {
        Banco banco = BancoTestUtil.bancoDoBrasil();
        
        bancoRepository.save(banco);
        
        Long id = banco.getId();
        
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete(uri, id)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(TestUtil.NOME_DE_USUARIO, TestUtil.ADMIN)                
                        .header(TestUtil.TOKEN, TestUtil.FUNCIONARIO_TOKEN))                  
                .andReturn();

        int status = result.getResponse().getStatus();
        
        Assert.assertEquals(HttpStatus.UNAUTHORIZED.value(), status);
    }  
    
    @Test
    public void testDeleteComBancoInexistente() throws Exception {
        Banco banco = BancoTestUtil.bancoDoBrasil();
        
        bancoRepository.save(banco);
        
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete(uri, 0)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(TestUtil.NOME_DE_USUARIO, TestUtil.ADMIN)                
                        .header(TestUtil.TOKEN, TestUtil.ADMIN_TOKEN))   
                .andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString(); 
        
        ErrorResponseDeserializer errorResponseDeserializer = super.mapFromJsonObject(content, ErrorResponseDeserializer.class);
        
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), status);
        Assert.assertEquals(TestUtil.NOT_FOUND_EXCEPTION, errorResponseDeserializer.getException());
        Assert.assertEquals(messageSource.getMessage("bancoNaoEncontrado", null, null), errorResponseDeserializer.getMessage());
    } 
    
    @Test
    public void testDeleteComSucesso() throws Exception {
        Banco banco = BancoTestUtil.bancoDoBrasil();
        banco.setAgencias(new ArrayList<>());
        
        bancoRepository.save(banco);
        
        Long id = banco.getId();
        
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete(uri, id)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(TestUtil.NOME_DE_USUARIO, TestUtil.ADMIN)                
                        .header(TestUtil.TOKEN, TestUtil.ADMIN_TOKEN)) 
                .andReturn();

        int status = result.getResponse().getStatus();
        
        Assert.assertEquals(HttpStatus.NO_CONTENT.value(), status);        
    }
}
