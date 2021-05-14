package fr.esgi.whatsupdocapi.doctors.infra.web.helper;

import fr.esgi.whatsupdocapi.doctors.infra.web.exception.IllegalArgumentsException;
import fr.esgi.whatsupdocapi.doctors.model.Doctor;

import java.util.Objects;

public class DoctorControllerHelper {

    public static void verifyPasswordValidity(String password, String confirmedPassword){
        if(!password.equals(confirmedPassword)){
            throw new IllegalArgumentsException("The confirmed password doesn't match the password.");
        }
    }

    public static void verifyUniqueEmailInRepository(String email, Doctor doctor){
        if(Objects.nonNull(doctor)){
            throw new IllegalArgumentsException("A user with this email address has already been created.");
        }
    }

}
