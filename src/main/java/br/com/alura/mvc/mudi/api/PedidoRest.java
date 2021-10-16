package br.com.alura.mvc.mudi.api;


import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("aguardando")
    private List<Pedido> getPedidosAguardandoOfertas(){
        Sort sort = Sort.by("id").descending();
        PageRequest paginacao = PageRequest.of(0,10,sort);

        return  pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);
    }

}
/**
 * Qual a diferença da nossa antiga implementação MVC com Spring e Thymeleaf para a funcionalidade REST que acabamos de desenvolver?
 *
 *
 *A diferença é que a nossa aplicação não é mais responsável pela camada de visão.
 * De fato, a nossa aplicação em Java com Spring apenas está processando o comportamento e retornando os
 * dados, mas não se preocupa mais em gerar a visão: o HTML
 *
 *  aprendemos como:
 * Criar um controlador específico para requisições REST
 * Usar o conceito REST para construir uma API devolvendo JSON
 * Usamos a anotação @RestController
 * Vimos que o Spring gera automaticamente o JSON
 */