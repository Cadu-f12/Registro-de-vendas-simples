package dao;

import model.venda.Pagamento;
import model.venda.Venda;
import model.venda.Vendedor;

import java.sql.Date;
import java.time.LocalDate;

public class ConversorVenda {
    private final Date novaData;
    private final String novoPagamento;
    private final String novoVendedor;

    public ConversorVenda(Venda venda) {
        this.novaData = converterData(venda.getData());
        this.novoPagamento = converterPagamento(venda.getFormaPagamento());
        this.novoVendedor = converterVendedor(venda.getNomeVendedor());
    }

    public Date converterData(LocalDate data) {
        return Date.valueOf(data);
    }
    public String converterPagamento(Pagamento pagamento) {
        return pagamento.name();
    }
    public String converterVendedor(Vendedor vendedor) {
        return vendedor.toString();
    }

    public Date getNovaData() {
        return novaData;
    }
    public String getNovoPagamento() {
        return novoPagamento;
    }
    public String getNovoVendedor() {
        return novoVendedor;
    }
}