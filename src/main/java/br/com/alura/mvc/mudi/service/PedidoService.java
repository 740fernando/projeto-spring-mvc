package br.com.alura.mvc.mudi.service;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface  PedidoService {
    List<Pedido> findByStatus(StatusPedido status, Pageable paginacao);
    Optional<Pedido> findById(Long pedidoId);
    List<Pedido> findAllByUsuario(Principal principal);
    List<Pedido> findByStatusEUsuario(StatusPedido status, String username);
    void save(Pedido pedido);
}
