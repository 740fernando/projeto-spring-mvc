package br.com.alura.mvc.mudi.model;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Pedido {

    private String nomeProduto;
    private BigDecimal valorNegociado;
    private LocalDate dateEntrega = LocalDate.now();
    private String urlProduto;
    private String urlImagem;
    private String descricao;

}
