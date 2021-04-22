package fr.esgi.whatsupdocapi.web.responses;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DoctorResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private String speciality;
    private String linkMedicalFile;
    // return /api/medical_files/id/

}
