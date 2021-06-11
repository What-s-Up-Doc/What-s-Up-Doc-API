package fr.esgi.whatsupdocapi.security.helper;

import fr.esgi.whatsupdocapi.core.exceptions.BadRequestException;
import fr.esgi.whatsupdocapi.core.exceptions.ConflictException;
import fr.esgi.whatsupdocapi.doctors.model.Doctor;

import java.util.Objects;

public class AccountControllerHelper {

    public static void verifyPasswordValidity(String password, String confirmedPassword) throws BadRequestException {
        if(!password.equals(confirmedPassword)){
            throw new BadRequestException("The confirmed password doesn't match the password.");
        }
    }

    public static void verifyUniqueEmailInRepository(String email, Doctor doctor) throws ConflictException {
        if(Objects.nonNull(doctor)){
            throw new ConflictException("A user with this email address has already been created.");
        }
    }

}
