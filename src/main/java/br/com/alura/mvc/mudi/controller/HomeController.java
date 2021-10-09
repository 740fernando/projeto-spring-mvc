package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


/**
 *  Como eu faço para poder me comunicar com o banco de dados usando o JPA? Nós utilizamos uma classe chamada
 *  de “endityManager” e vamos pedir para o “hibernate” configurar esse “endityManager” para nós usando um
 *  “@PersistenceContext”.
 */
@Controller
public class HomeController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping("/home")
    public String home(Model model){
        List<Pedido> pedidos = repository.recuperaTodosPedidos();
        model.addAttribute("pedidos",pedidos);
        return "home";
    }
}

/**
 Então esse “PedidoRepository” é quem está utilizando o “EndityManager” - de forma que “homeController”
 não tem mais esse acesso a “EndityManager”. Ele tem acesso ao “PedidoRepository”. Então eu vou injetar
 “PedidoRepository” e chamar só de “Repository” e vou tentar utilizar isso para recuperar todos os pedidos.
 */