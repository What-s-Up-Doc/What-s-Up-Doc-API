package fr.esgi.whatsupdocapi.patients.infra.web.exception;

public class IllegalArgumentsException extends RuntimeException {

    public IllegalArgumentsException(String message) {
        super(message);
    }
}
