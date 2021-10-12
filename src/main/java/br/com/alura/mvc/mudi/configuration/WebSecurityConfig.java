package br.com.alura.mvc.mudi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("fernando")
                        .password("123")
                        .roles("dev")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }


}
/**
 *  WebSecurityConfigurerAdapter, ele está dentro do próprio Spring Security, ou seja, é uma classe que
 *  vai nos ajudar a integrar nossa aplicação com Spring Security e ele também nos oferece uma anotação
 *  aqui para fazermos inicialização junto da camada de segurança da nossa aplicação.
 *  Ele está dizendo que ele está utilizando esse "http" e está chamando esse .authorizeRequests(),
 *  e que tudo o que for ("/", "/home") ele está permitindo tudo e qualquer outro request a pessoa tem
 *  que estar autenticada - ou seja, ele está dizendo que nesse exemplo o "/home" é público, a pessoa não
 *  precisa estar autenticada.
 */