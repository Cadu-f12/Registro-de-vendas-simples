package controller;

import dao.VendaDAO;
import model.venda.Venda;
import service.VendaService;

import java.util.ArrayList;

public class VendaController {
    public void registrarVenda(String inPagamento, String inVendedor, String inQuantidade, String inProduto, String inPreco) {
        // Normalizar dados
        NormalizadorRegistro normalizador = new NormalizadorRegistro(
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

    public ArrayList<Venda> carregarDados(String inData) {
        // Normalizar data na forma normal
        NormalizadorCaptura normalizador = new NormalizadorCaptura(inData);
        // Converter data para localDate
        ConversorDeCaptura conversor = new ConversorDeCaptura(normalizador.getData());
        // Enviar data normalizada e convertida ao service e logo em seguida dar retorno dos dados
        VendaService vendaService = new VendaService();
        return vendaService.carregarDados(conversor.getData());
    }

    public void deletarVenda(String id) {
        //Normalizar Id
        NormalizadorId normalizadorId = new NormalizadorId(id);
        // Converter dados
        ConversorDeId conversor = new ConversorDeId(id);
        // Enviar id normalizado ao service
        VendaService vendaService = new VendaService();
        vendaService.deletarVenda(conversor.getId());
    }
}