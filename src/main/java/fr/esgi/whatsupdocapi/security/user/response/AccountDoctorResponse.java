package fr.esgi.whatsupdocapi.security.user.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

@Data
@Accessors(chain = true)
public class AccountDoctorResponse extends RepresentationModel<AccountDoctorResponse> {
    private int id;
    private String email;
}
