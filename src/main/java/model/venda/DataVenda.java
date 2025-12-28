package model.venda;

import java.time.LocalDate;

public class DataVenda {
    private final LocalDate dataDaVenda;

    public DataVenda(LocalDate dataDaVenda) {
        validarDataFutura(dataDaVenda);
        validarDataVazia(dataDaVenda);

        this.dataDaVenda = dataDaVenda;
    }

    private void validarDataFutura(LocalDate dataDaVenda) {
        if (dataDaVenda.isAfter(LocalDate.now())) {
            throw new DataNoFuturoException("data inválida: a data está no futuro");
        }
    }
    private void validarDataVazia(LocalDate dataDaVenda) {
        if (dataDaVenda == null) {
            throw new AtributoVazioException("data inválida: atributo vazio ou nulo");
        }
    }

    public LocalDate getDataDaVenda() {
        return dataDaVenda;
    }

    @Override
    public String toString() {
        return "DataVenda{" +
                "dataDaVenda=" + dataDaVenda +
                '}';
    }
}