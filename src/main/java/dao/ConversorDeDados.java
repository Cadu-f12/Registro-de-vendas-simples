package dao;

import model.venda.Pagamento;
import model.venda.Venda;
import model.venda.Vendedor;

import java.sql.Date;
import java.time.LocalDate;

public class ConversorDeDados {
    private final Date novaData;
    private final String novoPagamento;
    private final String novoVendedor;

    public ConversorDeDados(Venda venda) {
        this.novaData = converterData(venda.getData());
        this.novoPagamento = converterPagamento(venda.getFormaPagamento());
        this.novoVendedor = converterVendedor(venda.getNomeVendedor());
    }

    private Date converterData(LocalDate data) {
        if (data == null) {
            return null;
        }
        return Date.valueOf(data);
    }
    private String converterPagamento(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }
        return pagamento.name();
    }
    private String converterVendedor(Vendedor vendedor) {
        if (vendedor == null) {
            return null;
        }
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