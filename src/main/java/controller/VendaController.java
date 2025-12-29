package controller;

import model.venda.Pagamento;
import model.venda.Vendedor;
import service.VendaService;

import java.math.BigDecimal;

public class VendaController {

    public void registrarVenda(String pagamento, String vendedor, String quantidade, String nomeProduto, String preco) {
        // Tratar exceções
        // Tratar mensagens vindas do service

        Pagamento novoPagamento = Pagamento.valueOf(pagamento);
        Vendedor novoVendedor = Vendedor.valueOf(vendedor);
        int novaQuantidade = Integer.parseInt(quantidade);
        String novoNomeProduto = nomeProduto.trim().toLowerCase();
        BigDecimal novoPreco = new BigDecimal(preco);

        VendaService vendaService = new VendaService();
        vendaService.registrarVenda(novoPagamento, novoVendedor, novaQuantidade, novoNomeProduto, novoPreco);
    }

}