package br.com.alura.mvc.mudi.dto;

import br.com.alura.mvc.mudi.model.Oferta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class RequisicaoNovoOferta {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //responsavel pelo padrao do formato da data

    private Long pedidoId;

    private String valor;

    private String dateEntrega;

    private String comentario;

    public Oferta toOferta() {

        Oferta oferta = new Oferta();
        oferta.setComentario(this.comentario);
        oferta.setDateEntrega(LocalDate.parse(this.dateEntrega));
        oferta.setValor(new BigDecimal(this.valor));

        return  oferta;
    }
}
