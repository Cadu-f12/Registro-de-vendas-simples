package controller;

public class NormalizadorCaptura {
    private final String data;

    public NormalizadorCaptura(String inData) {
        this.data = normalizarData(inData);
    }

    private String normalizarData(String data) {
        if (data == null) {
            return null;
        }
        // Lógica de pegar trechos da String e construir a forma normal
        return "20" + data.substring(6) + "-" + data.substring(3, 5) + "-" + data.substring(0, 2);
    }

    public String getData() {
        return data;
    }
}