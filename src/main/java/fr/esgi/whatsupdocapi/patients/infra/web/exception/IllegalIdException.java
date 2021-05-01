package fr.esgi.whatsupdocapi.patients.infra.web.exception;

public class IllegalIdException extends RuntimeException {

    public IllegalIdException(String message) {
        super(message);
    }
}
