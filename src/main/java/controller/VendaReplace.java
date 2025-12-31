package controller;

import java.text.Normalizer;

public class VendaReplace {
    private final String pagamento;
    private final String vendedor;
    private final String quantidade;
    private final String nomeProduto;
    private final String preco;

    public VendaReplace(String pagamento, String vendedor, String quantidade, String nomeProduto, String preco) {
        if (removerAcentos(pagamento).equals("Cartao de credito")) {
            this.pagamento = "cartao_credito";
        } else if (removerAcentos(pagamento).equals("Cartao de debito")) {
            this.pagamento = "cartao_debito";
        } else {
            this.pagamento = removerAcentos(pagamento);
        }


        this.vendedor = removerAcentos(vendedor);
        this.quantidade = quantidade;
        this.nomeProduto = removerAcentos(nomeProduto);

        this.preco = substituirVirgula(preco);
    }

    private String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }
    private String substituirVirgula(String preco) {
        for (char c : preco.toCharArray()) {
            if (c == ',') {
                return preco.replace(",", ".");
            }
        }
        return preco;
    }

    public String getPagamento() {
        return pagamento;
    }

    public String getVendedor() {
        return vendedor;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getPreco() {
        return preco;
    }
}