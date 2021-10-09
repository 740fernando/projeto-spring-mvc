package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public interface  PedidoRepository extends JpaRepository<Pedido,Long> {

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
*/
