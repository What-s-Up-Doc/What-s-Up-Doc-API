package fr.esgi.whatsupdocapi.security.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAccountDoctorRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String confirmedPassword;
    private String phone;
    private String gender;
    private String speciality;
}
