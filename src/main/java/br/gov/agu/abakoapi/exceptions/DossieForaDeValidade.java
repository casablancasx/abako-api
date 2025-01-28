package br.gov.agu.abakoapi.exceptions;

public class DossieForaDeValidade extends RuntimeException {
    public DossieForaDeValidade(String message) {
        super(message);
    }
}
