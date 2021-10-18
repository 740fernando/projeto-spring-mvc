package br.com.alura.mvc.mudi.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProduto;
    private BigDecimal valorNegociado;
    private LocalDate dateEntrega;
    private String urlProduto;
    private String urlImagem;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @ManyToOne(fetch = FetchType.LAZY)//muitos pedidos vai ter apenas um usuário
    @JsonIgnore
    private User user;




    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    public BigDecimal getValorNegociado() {
        return valorNegociado;
    }
    public void setValorNegociado(BigDecimal valorNegociado) {
        this.valorNegociado = valorNegociado;
    }
    public LocalDate getDataDaEntrega() {
        return dateEntrega;
    }
    public void setDataDaEntrega(LocalDate dataDaEntrega) {
        this.dateEntrega = dataDaEntrega;
    }
    public String getUrlProduto() {
        return urlProduto;
    }
    public void setUrlProduto(String urlProduto) {
        this.urlProduto = urlProduto;
    }
    public String getUrlImagem() {
        return urlImagem;
    }
    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public StatusPedido getStatus() {
        return status;
    }
    public void setStatus(StatusPedido status) {
        this.status = status;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
