package fr.esgi.whatsupdocapi.doctors.infra.web.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DoctorResponse {
    private int id;
    private String firstname;
    private String lastname;
    private String phone;
    private String gender;
    private String speciality;

}
