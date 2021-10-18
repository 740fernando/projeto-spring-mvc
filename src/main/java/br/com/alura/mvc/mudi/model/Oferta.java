package br.com.alura.mvc.mudi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Entity
public class Oferta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    private LocalDate dateEntrega;

    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY) //(fetch = FetchType.LAZY), para ele não carregar automaticamente quando buscar dados de ofertas e não carregar os dados do pedido também.
    @JsonIgnore
    private Pedido pedido;

}
/**
 * Só que ao mesmo tempo em que eu tenho o mapeamento de oferta com pedido, eu vou fazer
 * o mapeamento do pedido para a oferta. Quantas ofertas tem para o pedido? Muitas, então
 * um pedido tem uma lista de ofertas. Pode ter nenhuma, pode ter várias. E o mapeamento
 * vai ser @OneToMany
 */