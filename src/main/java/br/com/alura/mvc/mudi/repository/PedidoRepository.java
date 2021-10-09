package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.Pedido;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PedidoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Pedido> recuperaTodosPedidos () {

        Query query = entityManager.createQuery("select p from Pedido p", Pedido.class);
        return query.getResultList();
    }
}
/**
 * A “PedidoRepository” tem dependências que tem haver com repositório mesmo, com o fato do que ela é.
 * Por exemplo: ela precisa do um “EndityManager” que ela vai usar para acessar o repositório e tem
 * dependência de uma classe de negócio, a classe “Pedido”. Eu acho que em termos de dependências está
 * bom também. Ou seja, a nossa aplicação está bem organizada - e aí é interessante, veja que não estamos
 * precisando criar instâncias de “PedidoRepository”.
*/
