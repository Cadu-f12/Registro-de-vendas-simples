package model.venda;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda {
    private final Id id;
    private final DataVenda data;
    private final Pagamento formaPagamento;
    private final Vendedor nomeVendedor;
    private final Quantidade quantidade;
    private final Produto produto;
    private final Total total;

    public Venda(Id id, DataVenda data, Pagamento formaPagamento, Vendedor nomeVendedor, Quantidade quantidade, Produto produto, Total total) {
        this.id = id;
        this.data = data;
        this.formaPagamento = formaPagamento;
        this.nomeVendedor = nomeVendedor;
        this.quantidade = quantidade;
        this.produto = produto;
        this.total = total;
    }

    public int getId() {
        return this.id.getId();
    }

    public LocalDate getData() {
        return this.data.getDataDaVenda();
    }

    public Pagamento getFormaPagamento() {
        return this.formaPagamento;
    }

    public Vendedor getNomeVendedor() {
        return this.nomeVendedor;
    }

    public int getQuantidade() {
        return this.quantidade.getQuantidade();
    }

    public String getNomeProduto() {
        return this.produto.getNome();
    }

    public BigDecimal getTotal() {
        return this.total.getTotal();
    }
}