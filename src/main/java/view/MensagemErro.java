package view;

import model.venda.AtributoComSimbulosException;
import model.venda.AtributoForaDoIntervaloException;
import model.venda.AtributoVazioException;
import model.venda.DataNoFuturoException;

public class MensagemErro {
    private final String Mensagem;

    public MensagemErro(Exception e) {
        this.Mensagem = traduzirErro(e);
    }

    private String traduzirErro(Exception e) {
        String padronizacao = "\nPor favor insira novamente";
        // Para null ou vazio
        if (e instanceof AtributoVazioException) {
            if (e.getMessage().contains("nome")) {
                return "Nome do produto não informado!" + padronizacao;
            }
            if (e.getMessage().contains("preco")) {
                return "Preço do produto não informado!" + padronizacao;
            }
            if (e.getMessage().contains("tipo de pagamento")) {
                return "Forma de pagamento não informado!" + padronizacao;
            }
            if (e.getMessage().contains("vendedor")) {
                return "Nome do vendedor não informado!" + padronizacao;
            }
            if (e.getMessage().contains("data")) {
                return "Data da venda não informada!" + padronizacao;
            }
            return "Algum campo não foi informado!" + padronizacao;
        }
        // Para símbolos, caracteres especiais e digitos
        if (e instanceof AtributoComSimbulosException) {
            if (e.getMessage().contains("nome")) {
                return "Nome do produto com símbulos ou digitos!" + padronizacao;
            }
            return "Algum campo está com caracteres inválidos" + padronizacao;
        }
        // Para valor fora do limite
        if (e instanceof AtributoForaDoIntervaloException) {
            if (e.getMessage().contains("id_venda")) {
                return "Id com o número abaixo ou acima do limite" + padronizacao;
            }
            if (e.getMessage().contains("quantidade")) {
                return "Quantidade valor abaixo ou acima do limite" + padronizacao;
            }
            if (e.getMessage().contains("nome")) {
                return "Nome muito grande ou pequeno" + padronizacao;
            }
            if (e.getMessage().contains(("preco"))) {
                return "Preço com o valor abaixo ou acima do limite" + padronizacao;
            }

            return "Algum valor abaixo ou acima do limite";
        }
        //Para datas inseridas no futuro
        if (e instanceof DataNoFuturoException) {
            if (e.getMessage().contains("data")) {
                return "Data informada no futuro!" + padronizacao;
            }
        }
        return e.getMessage();
    }

    public String getMensagem() {
        return Mensagem;
    }
}