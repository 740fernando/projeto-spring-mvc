package br.com.alura.mvc.mudi.interceptor;

import lombok.Data;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InterceptadorDeAcessos extends HandlerInterceptorAdapter{

    public static List<Acesso> acessos = new ArrayList<Acesso>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Acesso acesso = new Acesso();
        acesso.path = request.getRequestURI(); // armazena a URI que o usuario esta tendando acessar
        acesso.data = LocalDateTime.now();
        request.setAttribute("acesso",acesso);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Acesso acesso = (Acesso)request.getAttribute("acesso");
        acesso.duracao = Duration.between(acesso.data,LocalDateTime.now());
        acessos.add(acesso);
    }
    @Data
    public static class Acesso {
        private String path;
        private LocalDateTime data;
        private Duration duracao;
    }
}