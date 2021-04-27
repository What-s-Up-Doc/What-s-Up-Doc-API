package fr.esgi.whatsupdocapi.patients.infra.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModifyPatientRequest {
    private String id;
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
