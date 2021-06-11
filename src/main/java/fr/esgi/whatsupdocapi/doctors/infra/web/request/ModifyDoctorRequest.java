package fr.esgi.whatsupdocapi.doctors.infra.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModifyDoctorRequest {
    private int id;
    private String firstname;
    private String lastname;
    private String phone;
    private String gender;
    private String speciality;
    private int accountId;
}
