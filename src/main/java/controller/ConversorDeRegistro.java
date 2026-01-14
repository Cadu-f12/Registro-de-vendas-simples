package controller;

import model.venda.Pagamento;
import model.venda.Vendedor;

import java.math.BigDecimal;

public class ConversorDeRegistro {
    private final Pagamento pagamento;
    private final Vendedor vendedor;
    private final int quantidade;
    private final String produto;
    private final BigDecimal preco;
    private final BigDecimal total;

    public ConversorDeRegistro(String inPagamento, String inVendedor, String inQuantidade, String inProduto, String inPreco, String inTotal) {
        // Converter todos os dados normalizados para seus tipos adequados
        this.pagamento = converterPagamento(inPagamento);
        this.vendedor = converterVendedor(inVendedor);
        this.quantidade = converterQuantidade(inQuantidade);
        this.produto = inProduto;
        this.preco = converterValorMonetario(inPreco);
        this.total = converterValorMonetario(inTotal);
    }

    private Pagamento converterPagamento(String pagamento) {
        if (pagamento == null) {
            return null;
        }
        return Pagamento.valueOf(pagamento);
    }
    private Vendedor converterVendedor(String vendedor) {
        if (vendedor == null) {
            return null;
        }
        return Vendedor.valueOf(vendedor);
    }
    private int converterQuantidade(String quantidade) {
        if (quantidade == null) {
            return 0;
        }
        return Integer.parseInt(quantidade);
    }
    private BigDecimal converterValorMonetario(String valor) {
        if (valor == null) {
            return null;
        }
        return new BigDecimal(valor);
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getProduto() {
        return produto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public BigDecimal getTotal() {
        return total;
    }
}