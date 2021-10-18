package br.com.alura.mvc.mudi.interceptor;

import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *  Nosso objetivo aqui é fazer um Interceptor com o qual consigamos guardar as informações das requisições
 *  do usuário, tempo de processamento e quais as páginas que são mais acessadas. Na verdade, quais as páginas
 *  mais acessadas, média de tempo são informações que geramos em cima do conteúdo que vamos guardar com o
 *  Interceptor
 *
 *  ! O controlador MVC continua cuidando dos dados da view e o redirecionamento, sem se preocupar como medir o tempo de execução. Cada classe com a sua responsabilidade.
 *
 * O interceptador é um dos padrões de projetos (Design Pattern) mais importantes do mundo Java e não é
 * exclusividade do Spring. Eles também existem para EJB, CDI, JSF e vários outros frameworks do mundo Java!
 */

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
/**
 * Aqui nós podemos implementar alguns métodos. O que vamos implementar aqui é o preHandler e o afterCompletion,
 * que se referem à antes de começar o processo, ou seja, antes de bater no Controller. Antes de chegar no
 * RestController, e depois que a resposta foi processada, que é o afterCompletion. Vou reimplementar o
 * preHandler e vou reimplementar o afterCompletion.
 *
 * Precisamos das informações de acesso do usuário, que é o momento em que ele está acessando uma determinada
 * página; e qual é a requisição que ele está fazendo, isso é o dado de acesso. Depois também vamos guardar a
 * duração disso, mas precisamos fazer isso nesse momento aqui, no afterCompletion
 *
 * class Acesso {. Nós vamos criar três atributos, que é o path que o usuário está tentando acessar, private
 * String path;. No momento que ele está tentando acessar, com LocalDateTime, private LocalDateTime data; e a
 * duração, com Duration, private Duration duracao;, do java.time também
 *
 * Você vê que no preHandler nós recebemos o HttpServletRequest, HttpServletResponse, o objeto handler aqui
 * que ele está processando e no afterCompletion a mesma coisa. Só que caso tenha dado um erro, ele recebe uma
 * exception também.
 *
 */