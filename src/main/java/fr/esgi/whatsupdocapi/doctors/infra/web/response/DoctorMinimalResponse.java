package fr.esgi.whatsupdocapi.doctors.infra.web.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

@Data
@Accessors(chain = true)
public class DoctorMinimalResponse extends RepresentationModel<DoctorMinimalResponse> {
    private String id;
    private String firstname;
    private String lastname;
}
