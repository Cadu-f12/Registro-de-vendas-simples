package model.venda;

import java.math.BigDecimal;

public class Total {
     private final BigDecimal total;

     public Total(Quantidade quantidade, Produto produto) {
         this.total = calcularTotal(quantidade, produto);
     }

     private BigDecimal calcularTotal(Quantidade quantidade, Produto produto) {
         int quantidadeC = quantidade.getQuantidade();
         BigDecimal preco = produto.getPreco();

         return preco.multiply(new BigDecimal(quantidadeC));
     }

     public BigDecimal getTotal() {
         return total;
     }

    @Override
    public String toString() {
        return "Total{" +
                "total=" + total +
                '}';
    }
}