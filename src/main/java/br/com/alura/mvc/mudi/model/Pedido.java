package br.com.alura.mvc.mudi.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProduto;
    private BigDecimal valorNegociado;
    private LocalDate dateEntrega = LocalDate.now();


    private String urlProduto;

    private String urlImagem;

    private String descricao;

}
