package br.com.alura.mvc.mudi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
/*
 Olhe só o que ele está fazendo, está dizendo que é considerado uma boa prática usar HTTP POST.
Se você não quiser usar HTTP POST, tem que usar outro método chamado de logoutRequestMatcher,
passando essa sintaxe aqui.
 */
    @Autowired
    private DataSource dataSource; // dataSource, que é onde ele consegue conexões com o banco de dados

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home/**") //criado a excessão / home/** (permit all)
                    .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/usuario/pedido", true) // essa linha que vai resolver seu problema, ela define qual é a página padrão da sua aplicação, então sempre que você logar ele vai te direcionar para a /caminho
                        .permitAll()
                ).logout(logout -> {
            logout.logoutUrl("/logout")
                    .logoutSuccessUrl("/home"); //define a pagina que irá ser direcionado quando deslogar

                });
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //um encoder bem forte, com senhas bem fortes; vai deixar nossa aplicação com uma boa segurança.

        /** cria o usuario
        UserDetails user=
             User.builder()
                .username("root")
                .password(encoder.encode("root"))
                .roles("ADM")
                .build();
         **/


        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder);//passwordEncoder, que é quem vai criptografar a senha
                //.withUser(user);

    }

}
