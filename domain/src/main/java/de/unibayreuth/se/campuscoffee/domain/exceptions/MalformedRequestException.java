package de.unibayreuth.se.campuscoffee.domain.exceptions;

public class MalformedRequestException extends RuntimeException {
    public MalformedRequestException(String message) {
        super(message);
    }
}
