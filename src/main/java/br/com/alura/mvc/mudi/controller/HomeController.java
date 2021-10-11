package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Locale;


/**
 *  Como eu faço para poder me comunicar com o banco de dados usando o JPA? Nós utilizamos uma classe chamada
 *  de “endityManager” e vamos pedir para o “hibernate” configurar esse “endityManager” para nós usando um
 *  “@PersistenceContext”.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * Utilizamos o @AutoWired para indicar ao Spring que o objeto anotado é um componente ou Bean dele e
     * que queremos que ele nos dê uma instância por meio do recurso de injeção de dependência.
     */
    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     *  preciso receber o modelo através do Model que recebemos como parâmetro e é preciso definir o nome da
     *  view no retorno do método. Veja a estrutura básica da nossa action:
     */

    @GetMapping
    public String home(Model model){
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos",pedidos);
        return "home";
    }


    @GetMapping("/{status}")
    public String porStatus(@PathVariable("status") String status, Model model){
        List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase(Locale.ROOT))); //transforma essa String em um status pedido
        model.addAttribute("pedidos",pedidos);
        model.addAttribute("status",status);
        return "home"; //preciso definir o nome da view no retorno do método.
    }

    //Existe uma anotação chamada de “@ExceptionHandler”, onde nós passamos qual é a
    // exceção que queremos mapear. É a “IllegalArgumentException”
    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/home";
    }
}

/**
 * Ou seja, para trabalhar com uma página, é preciso definir um ModeloEPagina e para tal existe uma classe
 * justamente com esse nome: ModelAndView. Veja a mesma action, mas usando ModelAndView do Spring MVC:
 *
 *
 Por exemplo: se fizermos um “pedidoRepository.findAll”, claramente ele vai retornar uma lista de todos
 os pedidos. Então pronto, é só isso! Veja que é bem mais simples. Não precisa nem daquela lista. Você
 vê que as dependências agora são para duas classes do Spring Data JPA. Quer dizer, é a “Repository”, a
 “JPA Repository” que é do Spring Data. O estereótipo que é para integrar com o Spring e o próprio “Pedido”,
 que é um repositório de “Pedidos”. Como ele fica simples, até as dependências ficam simples.

 Nós precisamos receber o “status” do usuário. Como fazemos para receber o “status” do usuário?
 Vamos fazer o seguinte: vamos colocar “StatusPedido.AGUARDANDO” e criar a declaração desse método
 da interface “Repository”.

 Então vou apertar as teclas “Ctrl + 1”, “Create method 'findByStatus'“ e ele já foi criado no nosso
 repositório, que é uma interface. De novo estamos utilizando, se você olhar a anotação...
 Esse “JpaRepository”, estamos vendo que é o Spring Data JPA. A implementação do Spring Data JPA
 olha para os nomes dos métodos que nós estamos declarando na interface. Então se declaramos um método
 que retorna uma lista de pedidos, ele sabe que vai fazer lá um “select *” na tabela “pedido”

   Se eu fizer uma requisição para “/home/aprovado”, o valor desse aqui é “/aprovado” e eu vou
 * pedir para o Spring injetar para mim o “status”. “Injete esse valor de ‘status’ aqui para mim”.
 * E aí eu vou indicar que esse parâmetro “status” tem que vir de uma variável que vem do “path”
 * chamada de “status”.
 Então o Spring, pega uma variável agora que eu declarei no “path” da URL e me dá dentro dessa
 variável “status”. E agora, o que eu vou fazer? Eu vou passar agora esse “findByStatus”.
 Em vez de passar o “StatusPedido.AGUARDANDO”, eu vou colocar o “status” aqui.

 se eu fizer “/home/aguardando”, ele mostra todos os pedidos aguardando.
 Se for “aprovado”, ele mostra o pedido “aprovado”. E se for, por exemplo, “rejeitado” -
 que é “status” que não mapeamos - ele volta para “/home” porque esse “status” não existe.
 Ele não encontra e ele lança um “IllegalArgumentException”. Ou seja, essa conversão lança
 um “IllegalArgumentException” porque agora nós estamos fazendo um tratamento para essa exceção.

 eu vou adicionar no “Model” mais um atributo chamado de “status”, onde eu vou passar esse valor
 do “status” que o usuário fez a requisição. Então agora tem um atributo novo chamado de “status”
 em que eu posso acessar o valor, por exemplo, “Aprovado”, “Entregue” e tal.

 Porque em “home”, eu vou fazer o seguinte: quando o “status” for “aguardando”,
 eu adiciono uma classe chamada de “active”. Então, como eu faço isso? “th:classapprend”
 e aí vou fazer o seguinte: se “status” for igual a “aguardando” eu adiciono a classe “active”.
 Então eu uso esse “classappend”. Então vamos clicar em “Aguardando”

 */
