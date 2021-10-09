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

    /**
     * Utilizamos o @AutoWired para indicar ao Spring que o objeto anotado é um componente ou Bean dele e
     * que queremos que ele nos dê uma instância por meio do recurso de injeção de dependência.
     */
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/home")
    public String home(Model model){
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos",pedidos);
        return "home";
    }
}

/**
 Por exemplo: se fizermos um “pedidoRepository.findAll”, claramente ele vai retornar uma lista de todos
 os pedidos. Então pronto, é só isso! Veja que é bem mais simples. Não precisa nem daquela lista. Você
 vê que as dependências agora são para duas classes do Spring Data JPA. Quer dizer, é a “Repository”, a
 “JPA Repository” que é do Spring Data. O estereótipo que é para integrar com o Spring e o próprio “Pedido”,
 que é um repositório de “Pedidos”. Como ele fica simples, até as dependências ficam simples.
 */