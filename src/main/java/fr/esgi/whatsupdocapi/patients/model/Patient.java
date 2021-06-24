package fr.esgi.whatsupdocapi.patients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Patient{
    private Integer id;
    private String firstname;
    private String lastname;
    private String phone;
    private String gender;
    private String birthday;
    private Integer smoker;
    private Double height;
    private Double weight;
    private String medical_history;
    private String family_medical_history;
    private String treatment;
    private Integer accountId;

    public boolean isValid() {
        return this.getId() != null &&
                this.getFirstname() != null &&
                this.getLastname() != null &&
                this.getPhone() != null &&
                this.getGender() != null &&
                this.birthday != null &&
                this.smoker != null &&
                this.weight != null &&
                this.height != null &&
                this.medical_history != null &&
                this.family_medical_history != null &&
                this.treatment != null &&
                this.accountId != null;
    }
}
