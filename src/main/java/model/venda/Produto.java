package model.venda;

import java.math.BigDecimal;

public class Produto {
    private final String nome;
    private final BigDecimal preco;

    public Produto(String nome, BigDecimal preco) {
        // Validações nome do produto
        validarNomeVazio(nome);
        validarSimbulos(nome);
        validarTamanhoNome(nome);
        // Validações preço
        validarPrecoVazio(preco);
        validarPrecoZerado(preco);

        this.nome = nome;
        this.preco = preco;
    }
    public Produto(String nome) {
        // Validações nome do produto
        validarNomeVazio(nome);
        validarSimbulos(nome);
        validarTamanhoNome(nome);

        this.nome = nome;
        this.preco = BigDecimal.ZERO;
    }

    private void validarSimbulos(String nome) {
        if(!nome.matches("^[a-z ]+$")) {
            throw new AtributoComSimbulosException("nome inválido: nome com simbolos ou números");
        }
    }
    private void validarNomeVazio(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new AtributoVazioException("nome inválido: atributo vazio ou nulo");
        }
    }
    private void validarTamanhoNome(String nome) {
        if (nome.length() < 3 || nome.length() > 75) {
            throw new AtributoForaDoIntervaloException("nome inválido: nome < 3 ou > 75");
        }
    }

    private void validarPrecoVazio(BigDecimal preco) {
        if (preco == null) {
            throw new AtributoVazioException("preço inválido: atributo vazio ou nulo");
        }
    }
    private void validarPrecoZerado(BigDecimal preco) {
        if (preco.compareTo(BigDecimal.ZERO) < 0 || preco.compareTo(new BigDecimal("9999.00")) >= 0) {
            throw new AtributoForaDoIntervaloException("preço inválido: preco < 0 ou >= 9999.00");
        }
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}