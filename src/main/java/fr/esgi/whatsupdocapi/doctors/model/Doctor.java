package fr.esgi.whatsupdocapi.doctors.model;

import fr.esgi.whatsupdocapi.core.User;
import lombok.Getter;

@Getter
public class Doctor extends User {
    private String speciality;

    public Doctor(String id, String firstname, String lastname, String email, String password, String phone, String gender, String speciality) {
        super(id, firstname, lastname, email, password, phone, gender);
        this.speciality = speciality;
    }
}