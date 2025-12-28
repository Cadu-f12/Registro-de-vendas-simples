package model.venda;

public class Quantidade {
    private final int quatidade;

    public Quantidade(int quantidade) {
        validarTamanhoQuantidade(quantidade);

        this.quatidade = quantidade;
    }

    private void validarTamanhoQuantidade(int quantidade) {
        if (quantidade <= 0 || quantidade >= 999) {
            throw new AtributoForaDoIntervaloException("quantidade inválida: quantidade <= 0 ou >= 999");
        }
    }

    public int getQuantidade() {
        return quatidade;
    }

    @Override
    public String toString() {
        return "Quantidade{" +
                "quatidade=" + quatidade +
                '}';
    }
}