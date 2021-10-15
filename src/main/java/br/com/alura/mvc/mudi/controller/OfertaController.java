package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//formulario para fazer ofertas para pedidos que estao aguardando
@Controller
@RequestMapping("/oferta")
public class OfertaController {

    @GetMapping
    public  String getFormularioDePedidosAguardando(){
        return "oferta/home";
    }
}
