package fr.esgi.whatsupdocapi.patients.model;

import fr.esgi.whatsupdocapi.core.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Patient extends User {
    private String birthday;
    private int smoker;
    private double height;
    private double weight;
    private String medical_history;
    private String family_medical_history;
    private String treatment;

    public Patient(int id, String firstname, String lastname, String email, String password, String phone,
                   String gender, String birthday, int smoker, double height, double weight, String medical_history, String family_medical_history, String treatment) {
        super(id, firstname, lastname, email, password, phone, gender);
        this.birthday = birthday;
        this.smoker = smoker;
        this.height = height;
        this.weight = weight;
        this.medical_history = medical_history;
        this.family_medical_history = family_medical_history;
        this.treatment = treatment;
    }
}
