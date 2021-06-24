package fr.esgi.whatsupdocapi.doctors.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Doctor{
    private Integer id;
    private String firstname;
    private String lastname;
    private String phone;
    private String gender;
    private String speciality;
    private Integer accountId;
}
