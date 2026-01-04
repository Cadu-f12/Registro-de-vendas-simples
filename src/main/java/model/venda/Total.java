package model.venda;

import java.math.BigDecimal;

public class Total {
     private final BigDecimal total;

     public Total(Quantidade quantidade, Produto produto) {
         this.total = calcularTotal(quantidade, produto);
     }
     public Total(BigDecimal total) {
         validarTotal(total);
         this.total = total;
     }

     private BigDecimal calcularTotal(Quantidade quantidade, Produto produto) {
         int quantidadeC = quantidade.getQuantidade();
         BigDecimal preco = produto.getPreco();

         return preco.multiply(new BigDecimal(quantidadeC));
     }
     private void validarTotal(BigDecimal total) {
         if (total.compareTo(BigDecimal.ZERO) < 0 || total.compareTo(new BigDecimal("99999")) > 0) {
             throw new AtributoForaDoIntervaloException("Total inválido: valor < 0 ou > 99999");
         }
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