package fr.esgi.whatsupdocapi.patients.infra.web.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PatientIdResponse {
    private int id;
}
