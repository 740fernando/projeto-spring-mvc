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
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
/*
 Olhe só o que ele está fazendo, está dizendo que é considerado uma boa prática usar HTTP POST.
Se você não quiser usar HTTP POST, tem que usar outro método chamado de logoutRequestMatcher,
passando essa sintaxe aqui.
 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true) // essa linha que vai resolver seu problema, ela define qual é a página padrão da sua aplicação, então sempre que você logar ele vai te direcionar para a /home
                        .permitAll()
                ).logout(logout -> logout.logoutUrl("/logout")
                ).csrf().disable();
    }
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("root")
                        .password("root")
                        .roles("ADM")
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
 *
 *  Com isso, nos foi disponibilizado todas as classes necessárias para configurar o módulo de segurança:
 *  utilizamos um adapter (WebSecurityConfigurerAdapter), que vem com várias configurações default. Apenas
 *  duas destas configurações nós precisamos reimplementar: configure(HttpSecurity) e configure(Authentica
 *  tionManagerBuilder auth). Utilizamos uma anotação (@EnableWebSecurity) que disponibiliza todas as depen-
 *  dências do Spring Security que precisamos para configurá-lo conforme a necessidade do nosso projeto;
 *  além disso, ganhamos alguns builders, que nos permitiu criar usuários e configurar a autorização.
 */