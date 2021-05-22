package fr.esgi.whatsupdocapi.patients.infra.web.helper;

import fr.esgi.whatsupdocapi.core.exceptions.BadRequestException;
import fr.esgi.whatsupdocapi.core.exceptions.ConflictException;
import fr.esgi.whatsupdocapi.patients.model.Patient;

import java.util.Objects;

public class PatientControllerHelper {

    public static void verifyPasswordValidity(String password, String confirmedPassword) {
        if (!password.equals(confirmedPassword)) {
            throw new BadRequestException("The confirmed password doesn't match the password.");
        }
    }

    public static void verifyUniqueEmailInRepository(String email, Patient patient) {
        if (Objects.nonNull(patient)) {
            throw new ConflictException("A user with this email address has already been created.");
        }
    }

}
