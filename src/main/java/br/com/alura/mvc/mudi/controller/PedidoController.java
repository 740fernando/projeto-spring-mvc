package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository; //comunicação com o banco de dados.

    @GetMapping("formulario")
    public String formulario(){
        return "pedido/formulario";
    }


    @PostMapping("novo")
    public String novo(RequisicaoNovoPedido requisicao){

        Pedido pedido = requisicao.toPedido(); //Basicamente, estamos convertendo uma requisição de “novo Pedido” para um “pedido” novo mesmo
        pedidoRepository.save(pedido);
        return "pedido/formulario";
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
 */