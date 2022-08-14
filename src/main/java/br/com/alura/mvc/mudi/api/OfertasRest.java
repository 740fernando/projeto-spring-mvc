package br.com.alura.mvc.mudi.api;


import br.com.alura.mvc.mudi.dto.RequisicaoNovoOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Oferta criaOferta(@Valid @RequestBody RequisicaoNovoOferta requisicao) {
        Optional<Pedido> pedidoBuscado = pedidoService.findById(requisicao.getPedidoId());
        return (!pedidoBuscado.isPresent()) ? null : getOferta(requisicao, pedidoBuscado);
    }

    private Oferta getOferta(RequisicaoNovoOferta requisicao, Optional<Pedido> pedidoBuscado) {
        Pedido pedido = pedidoBuscado.get();
        Oferta nova = requisicao.toOferta();
        nova.setPedido(pedido);
        pedido.getOfertas().add(nova);
        pedidoService.save(pedido);
        return nova;
    }
}
