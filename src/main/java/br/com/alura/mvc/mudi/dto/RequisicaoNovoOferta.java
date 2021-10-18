package br.com.alura.mvc.mudi.dto;

import br.com.alura.mvc.mudi.model.Oferta;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class RequisicaoNovoOferta {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //responsavel pelo padrao do formato da data


    private Long pedidoId;

    @Pattern(regexp = "^\\d+(\\.\\d+2)?$")
    @NotNull
    private String valor;

    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
    @NotNull
    private String dateEntrega;

    private String comentario;

    public Oferta toOferta() {

        Oferta oferta = new Oferta();
        oferta.setComentario(this.comentario);
        oferta.setDateEntrega(LocalDate.parse(this.dateEntrega, formatter));
        oferta.setValor(new BigDecimal(this.valor));

        return  oferta;
    }
}
