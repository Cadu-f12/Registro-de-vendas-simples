package service;

import dao.VendaDAO;
import model.venda.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class VendaService {

    public void registrarVenda(Pagamento inPagamento, Vendedor inVendedor, int inQuantidade, String inProduto, BigDecimal inPreco) {
        // Criar modelo completo
        Quantidade quantidade = new Quantidade(inQuantidade);
        Produto produto = new Produto(inProduto, inPreco);
        DataVenda data = new DataVenda(LocalDate.now());
        Total total = new Total(quantidade, produto);
        Venda venda = new Venda(null, data, inPagamento, inVendedor, quantidade, produto, total);
        // Enviar modelo ao DAO
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.registrarVenda(venda);
    }

    public ArrayList<Venda> carregarDados(LocalDate inData) {
        // Criar modelo somente com a data
        DataVenda data = new DataVenda(inData);
        Venda venda = new Venda(null, data, null, null, null, null, null);
        // Enviar modelo ao DAO
        VendaDAO vendaDAO = new VendaDAO();
        return vendaDAO.carregarDados(venda);
    }

    public Venda carregarVenda(int inId) {
        // Criar modelo apenas com o id
        Id id = new Id(inId);
        id.verificarExistenciaId();
        Venda venda = new Venda(id, null, null, null, null, null, null);
        // Enviar ao DAO
        VendaDAO vendaDAO = new VendaDAO();
        return vendaDAO.carregarVenda(venda);
    }

    public void atualizarVenda(int inId, LocalDate inData, Pagamento inPagamento, Vendedor inVendedor, String inProduto, int inQuantidade, BigDecimal inTotal) {
        // Criar modelo cheio
        Id id = new Id(inId);
        DataVenda data = new DataVenda(inData);
        Produto produto = new Produto(inProduto);
        Quantidade quantidade = new Quantidade(inQuantidade);
        Total total = new Total(inTotal);
        Venda venda = new Venda(id, data, inPagamento, inVendedor, quantidade, produto, total);
        // Enviar ao DAO
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.atualizarVenda(venda);
    }

    public void deletarVenda(int inId) {
        // Criar modelo apenas com Id
        Id id = new Id(inId);
        id.verificarExistenciaId();
        Venda venda = new Venda(id, null, null, null, null, null, null);
        // Enviar modelo ao DAO
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.deletarVenda(venda);
    }
}