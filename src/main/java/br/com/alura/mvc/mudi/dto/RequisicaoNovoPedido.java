package br.com.alura.mvc.mudi.dto;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import lombok.Data;

import javax.validation.constraints.NotBlank;


//Os atributos vão ter os nomes que eu estou mandando na requisição.
@Data
public class RequisicaoNovoPedido {

    @NotBlank
    private String nomeProduto;

    @NotBlank
    private String urlProduto;

    @NotBlank
    private String urlImagem;

    private String descricao;

    public Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setDescricao(descricao);
        pedido.setNomeProduto(nomeProduto);
        pedido.setUrlImagem(urlImagem);
        pedido.setUrlProduto(urlProduto);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        return pedido;
    }
}
/**
 * Então, olhe o que eu vou fazer: eu vou dizer que esse aqui vai receber uma “RequisicaoNovoPedido”.
 * Então “Requisicao”. Essa classe não existe. Eu vou criar dentro de um pacote, não no “controller”.
 * Vamos colocar no “dto”. O “dto data” transforma objetos. É uma classe que serve apenas transferir objetos.

 Quando convertemos uma “requisicaoNovoPedido” para “Pedido”, agora temos que setar o “status” do pedido.
 Então vamos colocar “pedido.setStatus(StatusPedido.AGUARDANDO)”.

 */