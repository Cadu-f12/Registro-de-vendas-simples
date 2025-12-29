package model.venda;

public enum Pagamento {
    pix,
    dinheiro,
    cartao_debito,
    cartao_credito;

    public static Pagamento validar(Pagamento pagamento) {
        if (pagamento == null) {
            throw new AtributoVazioException("tipo de pagamento inválido: o nome não deve ser null");
        }
        return pagamento;
    }
}