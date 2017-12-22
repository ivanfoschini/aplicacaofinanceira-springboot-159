package aplicacaofinanceira.interceptor;

import aplicacaofinanceira.service.UsuarioService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AutorizacaoInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UsuarioService usuarioService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {  
        String[] requestUri = request.getRequestURI().split("/");
        
        if (requestUri[1] == null || requestUri[2] == null) {
            return false;
        }
        
        if (usuarioService.autorizar("/" + requestUri[1] + "/" + requestUri[2], request.getHeader("nomeDeUsuario"), request.getHeader("token"))) {
            return true;
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {}
}
