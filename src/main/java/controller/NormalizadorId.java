package controller;

public class NormalizadorId {
    private final String id;

    public NormalizadorId(String id) {
        this.id = normalizarId(id);
    }

    private String normalizarId(String id) {
        if (id == null) {
            return null;
        }
        return id.trim();
    }

    public String getId() {
        return id;
    }
}