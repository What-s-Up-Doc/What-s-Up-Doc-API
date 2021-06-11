package fr.esgi.whatsupdocapi.patients.infra.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePatientRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String confirmedPassword;
    private String phone;
    private String gender;
    private String birthday;
    private int smoker;
    private double height;
    private double weight;
    private String medical_history;
    private String family_medical_history;
    private String treatment;
    private int accountId;
}
