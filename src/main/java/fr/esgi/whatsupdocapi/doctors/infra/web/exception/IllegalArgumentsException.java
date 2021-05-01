package fr.esgi.whatsupdocapi.doctors.infra.web.exception;

public class IllegalArgumentsException extends RuntimeException {

    public IllegalArgumentsException(String message) {
        super(message);
    }
}
