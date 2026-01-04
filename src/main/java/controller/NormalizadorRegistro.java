package controller;

import java.text.Normalizer;

public class NormalizadorRegistro {
    private final String pagamento;
    private final String vendedor;
    private final String quantidade;
    private final String produto;
    private final String preco;

    public NormalizadorRegistro(String inPagamento, String inVendedor, String inQuantidade, String inProduto, String inPreco) {
        /*
        Adaptar Enum, remover acentos, substituir virgula por ponto,
        normalizar dados deixando tudo em lowerCase e tirando espaços adjacentes
         */
        this.pagamento = adaptarEnum(removerAcentos(normalizarString(inPagamento)));
        this.vendedor = removerAcentos(normalizarString(inVendedor));
        this.quantidade = normalizarString(inQuantidade);
        this.produto = removerAcentos(normalizarString(inProduto));
        this.preco = substituirVirgula(normalizarString(inPreco));

    }

    private String adaptarEnum(String enumInvalido) {
        if (removerAcentos(enumInvalido).equals("cartao de credito")) {
            return "cartao_credito";
        } else if (removerAcentos(enumInvalido).equals("cartao de debito")) {
            return "cartao_debito";
        } else {
            return removerAcentos(enumInvalido);
        }
    }
    private String removerAcentos(String texto) {
        if (texto == null) {
            return null;
        }

        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }
    private String substituirVirgula(String preco) {
        if (preco == null) {
            return null;
        }

        for (char c : preco.toCharArray()) {
            if (c == ',') {
                return preco.replace(",", ".");
            }
        }
        return preco;
    }
    private String normalizarString(String texto) {
        if (texto == null) {
            return null;
        }
        return texto.toLowerCase().trim();
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

    public String getProduto() {
        return produto;
    }

    public String getPreco() {
        return preco;
    }
}