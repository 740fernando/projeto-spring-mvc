package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface  PedidoRepository extends JpaRepository<Pedido,Long> {

    List<Pedido> findByStatus(StatusPedido status, Pageable sort); //Pageable permite realizar a ordenação e contralar a numero de informaçoes

    @Query("select p from Pedido p join p.user u where u.username = :username")
    List<Pedido> findAllByUsuario(@Param("username")String username);

    @Query("select p from Pedido p join p.user u where u.username = :username and p.status = :status ")
    List<Pedido> findByStatusEUsuario(@Param("status")StatusPedido status, @Param("username")String username);
}

/**
 Para que esse “PedidoRepository” funcione integrado com o Spring Data, nós precisamos estender uma interface
 chamada de “JpaRepository” e ela precisa de dois tipos genéricos. Um é o tipo que ele está lidando, estou
 lindando com “Pedido”. O outro é o “ID” que é utilizado na classe “Pedido”. Se você for olhar, o tipo do
 “ID” é “Long”. Então você tem que definir esse aqui como tipo “Long”.
 ó que na hora que você estende “JpaRepository”, você vai herdar algumas definições de métodos que ele já tem.
 Uma delas é o próprio “findAll”. Nós nem sequer precisamos do método “findAll”. Então na classe “homeController”
 , nós em vez de chamarmos “recuperaTodosOsPedidos”, nós fazemos um “findAll”, que significa basicamente a mesma
 coisa. Principalmente se o nome do “Repository” deixar mais claro.


 “findByStatus” - ele sabe que tem que adicionar um filtro, um “[WHERE]” onde “status” é igual ao “status”
 que estamos passando aqui. Não vou colocar “aguardando”, vou colocar “status”. Então o próprio Spring Data
 JPA vai conseguir implementar esse “select” para nós. Essa é uma das grandes vantagens de utilizar o
 Spring Data, porque ele simplifica bastante essa consulta.

 /**
 * Então já fizemos o select pedido pelo username, colocamos um and aqui e digitamos p.status :status.
 * Esse @Query("select p from Pedido p join p.user u where u.username = :username and p.status :status)
 * vai estar mapeado para esse atributo, status, e o primeiro vai estar mapeado para esse daqui, username
 */

