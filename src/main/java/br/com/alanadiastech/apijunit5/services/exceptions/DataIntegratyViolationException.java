package br.com.alanadiastech.apijunit5.services.exceptions;

public class DataIntegratyViolationException extends  RuntimeException {

    public DataIntegratyViolationException(String message) {
        super(message);
    }
}
