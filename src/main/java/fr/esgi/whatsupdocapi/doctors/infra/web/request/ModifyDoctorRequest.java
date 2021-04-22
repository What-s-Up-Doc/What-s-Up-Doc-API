package fr.esgi.whatsupdocapi.doctors.infra.web.request;

import lombok.Data;

@Data
public class ModifyDoctorRequest {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private String speciality;
}
