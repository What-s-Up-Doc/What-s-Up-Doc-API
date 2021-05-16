package fr.esgi.whatsupdocapi.appointments.infra.web.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Data
public class AppointmentIdResponse extends RepresentationModel<AppointmentIdResponse> {
    private Integer appointment_id;
}
