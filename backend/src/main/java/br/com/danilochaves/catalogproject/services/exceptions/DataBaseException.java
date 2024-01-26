package br.com.danilochaves.catalogproject.services.exceptions;

public class DataBaseException extends RuntimeException{
    public DataBaseException(String message) {
        super(message);
    }
}
