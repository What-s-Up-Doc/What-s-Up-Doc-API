package fr.esgi.whatsupdocapi.web.requests;

import lombok.Data;

@Data
public class CreatePatientRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private String birthday;
    private boolean smoker;
    private double height;
    private double weight;
    private String medical_history;
    private String family_medical_history;
    private String traitement;
}
