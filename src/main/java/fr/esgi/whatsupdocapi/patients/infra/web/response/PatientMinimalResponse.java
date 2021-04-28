package fr.esgi.whatsupdocapi.patients.infra.web.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

@Data
@Accessors(chain = true)
public class PatientMinimalResponse extends RepresentationModel<PatientMinimalResponse> {
    private String id;
    private String firstname;
    private String lastname;
}
