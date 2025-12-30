package service;

import dao.VendaDAO;
import model.venda.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VendaService {

    public void registrarVenda(Pagamento formaPagamento, Vendedor vendedor, int quantidade, String nomeProduto, BigDecimal preco) {
        VendaDAO vendaDAO = new VendaDAO();
        try {
            Produto produtoValue = new Produto(nomeProduto, preco);
            Quantidade quantidadeValue = new Quantidade(quantidade);

            Venda venda = new Venda(null, new DataVenda(LocalDate.now()), formaPagamento, vendedor, quantidadeValue, produtoValue, new Total(quantidadeValue, produtoValue));
            vendaDAO.registrarVenda(venda);
        } catch (AtributoVazioException | AtributoForaDoIntervaloException | DataNoFuturoException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException("Erro genérico: " + e.getMessage());
        }
    }
}