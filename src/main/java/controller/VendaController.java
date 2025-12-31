package controller;

import model.venda.Pagamento;
import model.venda.Vendedor;
import service.VendaService;

import java.math.BigDecimal;

public class VendaController {
    public void registrarVenda(VendaReplace vendaReplace) {
        // Tratar exceções
        // Tratar mensagens vindas do service

        Pagamento novoPagamento = Pagamento.valueOf(vendaReplace.getPagamento().toLowerCase());
        Vendedor novoVendedor = Vendedor.valueOf(vendaReplace.getVendedor().toLowerCase());
        int novaQuantidade = Integer.parseInt(vendaReplace.getQuantidade());
        String novoNomeProduto = vendaReplace.getNomeProduto().trim().toLowerCase();
        BigDecimal novoPreco = new BigDecimal(vendaReplace.getPreco());

        VendaService vendaService = new VendaService();
        vendaService.registrarVenda(novoPagamento, novoVendedor, novaQuantidade, novoNomeProduto, novoPreco);
    }
}