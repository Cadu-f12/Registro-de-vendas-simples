package controller;

import model.venda.Pagamento;
import model.venda.Vendedor;
import service.VendaService;

import java.math.BigDecimal;

public class VendaController {
    public void registrarVenda(String inPagamento, String inVendedor, String inQuantidade, String inProduto, String inPreco) {
        // Normalizar dados
        NormalizadorVenda normalizador = new NormalizadorVenda(
                inPagamento, inVendedor, inQuantidade, inProduto, inPreco
        );
        // Converter dados
        ConversorDeRegistro conversor = new ConversorDeRegistro(
                normalizador.getPagamento(), normalizador.getVendedor(),
                normalizador.getQuantidade(), normalizador.getProduto(), normalizador.getPreco()
        );
        // Enviar dados normalizados e convertidos ao service
        VendaService vendaService = new VendaService();
        vendaService.registrarVenda(
                conversor.getPagamento(), conversor.getVendedor(),
                conversor.getQuantidade(), conversor.getProduto(), conversor.getPreco()
        );
    }
}