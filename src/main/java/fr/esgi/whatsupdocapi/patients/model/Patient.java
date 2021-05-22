package fr.esgi.whatsupdocapi.patients.model;

import fr.esgi.whatsupdocapi.core.model.User;
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
    private Integer smoker;
    private Double height;
    private Double weight;
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

    public boolean isValid() {
        return this.getId() != null &&
                this.getFirstname() != null &&
                this.getLastname() != null &&
                this.getEmail() != null &&
                this.getPassword() != null &&
                this.getPhone() != null &&
                this.getGender() != null &&
                this.birthday != null &&
                this.smoker != null &&
                this.weight != null &&
                this.height != null &&
                this.medical_history != null &&
                this.family_medical_history != null &&
                this.treatment != null;
    }
}
