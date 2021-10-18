package br.com.alura.mvc.mudi.api;


import br.com.alura.mvc.mudi.dto.RequisicaoNovoOferta;
import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public Oferta criaOferta(@RequestBody RequisicaoNovoOferta requisicao){

       Optional<Pedido> pedidoBuscado = pedidoRepository.findById(requisicao.getPedidoId());
       if(!pedidoBuscado.isPresent()){
           return null;
       }
       Pedido pedido = pedidoBuscado.get();

       Oferta nova = requisicao.toOferta();
       nova.setPedido(pedido);
       pedido.getOfertas().add(nova);
       pedidoRepository.save(pedido);

       return nova;
    }
}
/**
 * ao pedido eu vou adicionar aqui no pedido.getOfertas, Criei getters e setters para os dois.
 * Digito um getOfertas().add(nova); e a nova oferta. Associei um com o outro e a única coisa
 * que eu preciso fazer é salvar o pedido. Então pedidoRepository.save e passo o (pedido);
 *
 *  "Mas eu não preciso salvar a oferta também?" Não precisa, automaticamente ele já vai na hora em que
 *  você salvar o pedido. Como ele está com cascade all ele já vai salvar também para persistence. Nós vamos
 *  persistir isso. Eu só digito um return nova;
 *
 * Então já temos o endpoint que conseguimos salvar a oferta.
 *
 *
 * @RequestBody-  é a anotação que indicamos exatamente isso, pega os dados da requisição e adiciona nesse aqui
 *
 */