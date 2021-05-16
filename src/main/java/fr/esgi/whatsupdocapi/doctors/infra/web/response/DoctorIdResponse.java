package fr.esgi.whatsupdocapi.doctors.infra.web.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DoctorIdResponse {
    private int id;
}
