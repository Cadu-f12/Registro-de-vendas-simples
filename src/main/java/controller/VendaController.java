package controller;

import dao.VendaDAO;
import model.venda.Pagamento;
import model.venda.Venda;
import model.venda.Vendedor;
import service.VendaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class VendaController {
    public void registrarVenda(String inPagamento, String inVendedor, String inQuantidade, String inProduto, String inPreco) {
        // Normalizar dados
        NormalizadorRegistro normalizador = new NormalizadorRegistro(
                inPagamento, inVendedor, inQuantidade, inProduto, inPreco, null
        );
        // Converter dados
        ConversorDeRegistro conversor = new ConversorDeRegistro(
                normalizador.getPagamento(), normalizador.getVendedor(),
                normalizador.getQuantidade(), normalizador.getProduto(), normalizador.getPreco(), null
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

    public Venda carregarVenda(String inId) {
        // Normalizar Id
        NormalizadorId normalizador = new NormalizadorId(inId);
        // Converter Id
        ConversorDeId conversor = new ConversorDeId(normalizador.getId());
        // Enviar Id normalizador e convertido ao service
        VendaService vendaService = new VendaService();
        return vendaService.carregarVenda(conversor.getId());
    }

    public void atualizarVenda(String inId, String inData, String inPagamento, String inVendedor, String inProduto, String inQuantidade, String inTotal) {
        // Normalizar todos os dados
        NormalizadorRegistro normalizadorR = new NormalizadorRegistro(
                inPagamento, inVendedor, inQuantidade, inProduto, null, inTotal
        );
        NormalizadorCaptura normalizadorC = new NormalizadorCaptura(
                inData
        );
        NormalizadorId normalizadorI = new NormalizadorId(
                inId
        );
        // Conversão do registro
        ConversorDeRegistro conversorR = new ConversorDeRegistro(
                normalizadorR.getPagamento(), normalizadorR.getVendedor(),
                normalizadorR.getQuantidade(), normalizadorR.getProduto(), null, normalizadorR.getTotal()
        );
        ConversorDeCaptura conversorC = new ConversorDeCaptura(
                normalizadorC.getData()
        );
        ConversorDeId conversorI = new ConversorDeId(
                normalizadorI.getId()
        );
        // Enviar a nova venda ao Service
        VendaService vendaService = new VendaService();
        vendaService.atualizarVenda(
                conversorI.getId(), conversorC.getData(), conversorR.getPagamento(), conversorR.getVendedor(),
                conversorR.getProduto(), conversorR.getQuantidade(), conversorR.getTotal()
        );
    }

    public void deletarVenda(String id) {
        //Normalizar Id
        NormalizadorId normalizadorId = new NormalizadorId(id);
        // Converter dados
        ConversorDeId conversor = new ConversorDeId(normalizadorId.getId());
        // Enviar id normalizado ao service
        VendaService vendaService = new VendaService();
        vendaService.deletarVenda(conversor.getId());
    }
}