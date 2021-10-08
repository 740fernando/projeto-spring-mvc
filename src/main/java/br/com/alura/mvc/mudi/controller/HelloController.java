package br.com.alura.mvc.mudi.controller;

//Nessa classe é onde nós vamos adicionar as nossas “actions”.

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Como eu faço para essa requisição “get” chamada “Hello” porque é um path, com o path “hello”
 * que bata nesse método aqui. Como eu aviso para o Spring? Se você receber um “@GetMapping(“/hello”)”.
 * Chame esse método e é esse método que vai processar.
 * O Spring nos dá uma interface chamada “model” onde podemos adicionar valores nele. Em vez de usar o
 * “request”, use o “model” chamando o método “addAttribute”. Ele funciona do mesmo jeito que o
 * HttpServletRequest” para esse caso, só que sem ter que ficar utilizando uma camada mais abaixo, uma
 * camada de “Servlets”. Nós estamos só no nível do Spring. Então não precisamos mexer no “HttpServletRequest”.
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("nome","Mundo");
        return "hello";
    }
}
/**
 * Como estamos trabalhando com Thymeleaf, ele por padrão, as nossas páginas em Thymeleaf são HTMLs normais.
 * Então, “hello.html”. Nós podemos criar um HTML simples. Vou colocar um “” com ““ para nós não termos problema
 * de acentuação. Eu vou criar um “body” com a mensagem “return=hello”;”. Como eu faço para chegar nessa “view”?
 * Uma requisição que bate no Spring teria que ser direcionada para esse método “hello”.
 *
 * O método “hello”, que é uma “action”, no caso da nossa implementação as “actions” são métodos de
 * classes “controllers” e o retorno dessa “action” é o nome da “view” que deve ser processada, cuja requisição
 * deve ser redirecionada para gerar o HTML que vai ser devolvido para o usuário.
 */