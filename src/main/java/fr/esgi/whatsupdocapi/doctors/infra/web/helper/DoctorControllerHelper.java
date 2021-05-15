package fr.esgi.whatsupdocapi.doctors.infra.web.helper;

import fr.esgi.whatsupdocapi.doctors.infra.web.exception.EmailAlreadyUsedException;
import fr.esgi.whatsupdocapi.doctors.infra.web.exception.IllegalArgumentsException;
import fr.esgi.whatsupdocapi.doctors.infra.web.exception.PasswordsDontMatchException;
import fr.esgi.whatsupdocapi.doctors.model.Doctor;

import java.util.Objects;

public class DoctorControllerHelper {

    public static void verifyPasswordValidity(String password, String confirmedPassword) throws PasswordsDontMatchException{
        if(!password.equals(confirmedPassword)){
            throw new PasswordsDontMatchException("The confirmed password doesn't match the password.");
        }
    }

    public static void verifyUniqueEmailInRepository(String email, Doctor doctor) throws EmailAlreadyUsedException {
        if(Objects.nonNull(doctor)){
            throw new EmailAlreadyUsedException("A user with this email address has already been created.");
        }
    }

}
