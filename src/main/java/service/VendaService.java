package service;

import dao.VendaDAO;
import model.venda.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VendaService {

    public void registrarVenda(Pagamento inPagamento, Vendedor inVendedor, int inQuantidade, String inProduto, BigDecimal inPreco) {
        // Criar modelo
        Quantidade quantidade = new Quantidade(inQuantidade);
        Produto produto = new Produto(inProduto, inPreco);
        DataVenda data = new DataVenda(LocalDate.now());
        Total total = new Total(quantidade, produto);
        Venda venda = new Venda(null, data, inPagamento, inVendedor, quantidade, produto, total);
        // Enviar modelo ao DAO
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.registrarVenda(venda);
    }
}