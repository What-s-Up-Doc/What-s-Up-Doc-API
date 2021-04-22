package fr.esgi.whatsupdocapi.patients.model;

import fr.esgi.whatsupdocapi.core.User;
import lombok.Getter;

@Getter
public class Patient extends User {
    private String birthday;
    private boolean smoker;
    private double height;
    private double weight;
    private String medical_history;
    private String family_medical_history;
    private String traitement;

    public Patient(String id, String firstname, String lastname, String email, String password, String phone, String gender, String birthday, boolean smoker, double height, double weight, String medical_history, String family_medical_history, String traitement) {
        super(id, firstname, lastname, email, password, phone, gender);
        this.birthday = birthday;
        this.smoker = smoker;
        this.height = height;
        this.weight = weight;
        this.medical_history = medical_history;
        this.family_medical_history = family_medical_history;
        this.traitement = traitement;
    }
}
