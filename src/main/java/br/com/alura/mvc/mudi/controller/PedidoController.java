package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


/**
 * A anotação @RequestMapping vai muito além da configuração da rota, ela na verdade é muito poderosa e
 * permite definir detalhes sobre o mapeamento da requisição
 */

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository; //comunicação com o banco de dados.

    @Autowired
    private UserRepository userRepository;

    @GetMapping("formulario")
    public String formulario(RequisicaoNovoPedido requisicao){
        return "pedido/formulario";
    }

    /**
     * através de um objeto do tipo “BindingResult”, vai nos dar o resultado dessa validação.
     * Se tiver erros, ele vai nos dar através de um método chamado “hasErrors”, vai nos dizer “true”
     * ou “false” (“verdadeiro” ou “falso”), se teve erro ou não.
     */


    @PostMapping("novo")
    public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result){
        if(result.hasErrors()){
            return "pedido/formulario";
        }
        //buscar o usuário associado à requisição
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); //que nos dá essa informação através desse getAuthentication(). Então getContext de segurança, getAuthentication, os dados do usuário mesmo e você pode pedir, através do método getName() o username do usuário. Vou criar até uma variável aqui chamada de username
        User user = userRepository.findByUsername(username);

        Pedido pedido = requisicao.toPedido(); //Basicamente, estamos convertendo uma requisição de “novo Pedido” para um “pedido” novo mesmo
        pedido.setUser(user);
        pedidoRepository.save(pedido);
        return "redirect:/home";
    }
}
/**
 * Aqui no início tem “/pedido/novo”, método “post” e as informações são passadas no “Form Data”.
 * Então esses são os dados que preenchemos na tela, que o usuário preencheu. Ele vai com esses valores,
 * “nomeProduto”, valor do “produto”, “UrlProduto” e tem isso que o usuário preencheu que estava no “input”.
 * Como recebemos essas informações? Então, primeiro precisamos de alguém que responda a requisição
 * “/pedido/novo”. Eu vou criar o método “public String” porque depois esse “String”, esse retorno tem
 * haver com a página que vai aparecer depois que eu salvar o pedido. Vamos ver isso depois também.
 * Então, “novo()
 *
 * Esta classe foi utilizada no controller de pedidos apenas para buscar o usuário associado à requisição.
 * Nenhuma classe Repository, nem a classe Pedido, nem User conhece a SpringContextHolder. Portanto, o modelo
 * da aplicação Mudi (Repository, Pedido e User) desconhece o Spring Security.
 */
