package service;

import model.venda.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VendaService {

    public void RegistrarVenda(Pagamento formaPagamento, Vendedor vendedor, int quantidade, String nomeProduto, String preco) {
        try {
            Produto produtoValue = new Produto(nomeProduto, new BigDecimal(preco));
            Quantidade quantidadeValue = new Quantidade(quantidade);

            Venda venda = new Venda(null, new DataVenda(LocalDate.now()), formaPagamento, vendedor, quantidadeValue, produtoValue, new Total(quantidadeValue, produtoValue));
        } catch (AtributoVazioException | AtributoForaDoIntervaloException | DataNoFuturoException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException("Erro genérico: " + e.getMessage());
        }
    }
}