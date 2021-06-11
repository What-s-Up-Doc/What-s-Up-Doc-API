package fr.esgi.whatsupdocapi.security.user.account.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

@Data
@Accessors(chain = true)
public class AccountPatientResponse extends RepresentationModel<AccountPatientResponse> {
    private int id;
    private String email;
}
