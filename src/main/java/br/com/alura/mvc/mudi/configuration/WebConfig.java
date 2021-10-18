package br.com.alura.mvc.mudi.configuration;

import br.com.alura.mvc.mudi.interceptor.InterceptadorDeAcessos;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptadorDeAcessos()).addPathPatterns("/**");
    }
}
/**
 * Já temos o Interceptor configurado, só que esse Interceptor não vai funcionar automaticamente.
 * Precisamos de alguma coisa para habilitar esse interceptador, para ele passar a funcionar.
 * Por isso fazemos uma classe de configuração. Eu vou chamá-la de WebConfig e vou estender aqui
 * uma classe que nos ajuda a fazer a configuração de Interceptor, que é a WebMvcConfigurationSupport.
 * É só anotar aqui como @Configuration, para ser reconhecido aqui pelo Spring.
 * E nós implementamos o método addInterceptors, que já recebe o InterceptorRegistry e a única coisa que
 * precisamos fazer é digitarmos registry.addInterceptor(new InterceptadorDeAcessos()). Passamos também
 * qual é o path que ele vai interceptar, ou seja, que requisições ele vai interceptar. Podemos colocar
 * aqui todas as requisições.
 * Lembrando que você pode passar vários paths diferentes, que ele recebe uma lista de paths aqui.
 * Então pronto, é isso! Você pode passar uma ou mais configurações dessa daqui, de pattern
 */