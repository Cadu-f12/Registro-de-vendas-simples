package model.venda;

public enum Vendedor {
    carlos,
    viviane,
    helena;

    public static Vendedor validar(Vendedor vendedor) {
        if (vendedor == null) {
            throw new AtributoVazioException("vendedor inválido: o nome não deve ser null");
        }
        return vendedor;
    }
}