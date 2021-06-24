package fr.esgi.whatsupdocapi.patients.infra.web.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PatientResponse {
    //TODO adding email account?
    private int id;
    private String firstname;
    private String lastname;
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
