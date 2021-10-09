package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {


    @GetMapping("/home")
    public String home(Model model){
        Pedido pedido = new Pedido();
        pedido.setNomeProduto("Pizza de Mussarela");
        pedido.setUrlImagem("https://m.media-amazon.com/images/I/51+TWOfdtiL._AC_SL1000_.jpg");
        pedido.setUrlProduto("https://www.amazon.com/All-new-Kindle-Paperwhite-Waterproof-International/dp/B07741S7Y8/ref=pd_sbs_2/134-7036161-8654431?pd_rd_w=V84bL&pf_rd_p=3676f086-9496-4fd7-8490-77cf7f43f846&pf_rd_r=9MVP3CR3PXZC5T1Q333G&pd_rd_r=4889b014-1ea5-4cc1-a4b0-46114f1e220f&pd_rd_wg=CByDR&pd_rd_i=B07741S7Y8&psc=1");
        pedido.setDescricao("uma descrição qualquer para esse pedido");

        List<Pedido> pedidos=Arrays.asList(pedido);
        model.addAttribute("pedidos",pedidos);
        return "home";
    }
}

