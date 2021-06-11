package fr.esgi.whatsupdocapi.security.user.account.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModifyAccountPatientRequest {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private String birthday;
    private int smoker;
    private double height;
    private double weight;
    private String medical_history;
    private String family_medical_history;
    private String treatment;
}
