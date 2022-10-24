package br.com.alanadiastech.apijunit5.services.exceptions;

public class ObjectNotFoundException extends  RuntimeException {

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
