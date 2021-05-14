package fr.esgi.whatsupdocapi.doctors.infra.web.helper;

import fr.esgi.whatsupdocapi.doctors.model.Doctor;

import java.util.Objects;

public class DoctorControllerHelper {

    public static Boolean verifyPasswordValidity(String password, String confirmedPassword){
        if(password.equals(confirmedPassword)) return true;
        return false;
    }

    public static Boolean verifyUniqueEmailInRepository(String email, Doctor doctor){
        if(Objects.nonNull(doctor)) return false;
        return true;
    }

}
