package controller;

import java.time.LocalDate;

public class ConversorDeCaptura {
    private final LocalDate data;

    public ConversorDeCaptura(String inData) {
        this.data = converterData(inData);
    }

    private LocalDate converterData(String data) {
        if (data == null) {
            return null;
        }
        return LocalDate.parse(data);
    }

    public LocalDate getData() {
        return data;
    }
}