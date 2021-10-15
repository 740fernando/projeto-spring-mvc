package br.com.alura.mvc.mudi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    private String username;
    private String password;
    private Boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch= FetchType.LAZY)
    private List<Pedido> pedidos;
}

/**
 *  Nenhuma classe da nossa aplicação tem qualquer dependência com o Spring Security.
 *  A classe User, que usamos para mapear a tabela usada pelo Spring Security, pode ser alterada conforme
 *  a necessidade da nossa aplicação.
 */