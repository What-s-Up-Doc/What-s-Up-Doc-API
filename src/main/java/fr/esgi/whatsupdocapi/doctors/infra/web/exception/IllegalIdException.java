package fr.esgi.whatsupdocapi.doctors.infra.web.exception;

public class IllegalIdException extends RuntimeException {

    public IllegalIdException(String message) {
        super(message);
    }
}
