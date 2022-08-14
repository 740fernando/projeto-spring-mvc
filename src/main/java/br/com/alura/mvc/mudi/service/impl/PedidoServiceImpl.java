package br.com.alura.mvc.mudi.service.impl;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.service.PedidoService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> findByStatus(StatusPedido status, Pageable paginacao) {
        return pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);
    }

    @Override
    public Optional<Pedido> findById(Long pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

    @Override
    public List<Pedido> findAllByUsuario(Principal principal) {
        return pedidoRepository.findAllByUsuario(principal.getName());
    }

    @Override
    public List<Pedido> findByStatusEUsuario(StatusPedido status, String username){
        return pedidoRepository.findByStatusEUsuario(status,username);
    }

    @Override
    public void save(Pedido pedido) {
        pedidoRepository.save(pedido);
    }
}