package controller;

public class ConversorDeId {
    private final int id;

    public ConversorDeId(String id) {
        this.id = converterId(id);
    }

    private int converterId(String inId) {
        if (inId == null) {
            return 0;
        }
        return Integer.parseInt(inId);
    }

    public int getId() {
        return id;
    }
}