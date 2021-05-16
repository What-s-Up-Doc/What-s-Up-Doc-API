package fr.esgi.whatsupdocapi.patients.infra.web.helper;

import fr.esgi.whatsupdocapi.doctors.infra.web.exception.EmailAlreadyUsedException;
import fr.esgi.whatsupdocapi.doctors.infra.web.exception.PasswordsDontMatchException;
import fr.esgi.whatsupdocapi.patients.model.Patient;

import java.util.Objects;

public class PatientControllerHelper {

    public static void verifyPasswordValidity(String password, String confirmedPassword) {
        if (!password.equals(confirmedPassword)) {
            throw new PasswordsDontMatchException("The confirmed password doesn't match the password.");
        }
    }

    public static void verifyUniqueEmailInRepository(String email, Patient patient) {
        if (Objects.nonNull(patient)) {
            throw new EmailAlreadyUsedException("A user with this email address has already been created.");
        }
    }

}
