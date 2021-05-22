package fr.esgi.whatsupdocapi.appointments.infra.web.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentDetailResponse extends RepresentationModel<AppointmentDetailResponse> {
    private Integer doctorId;
    private Integer patientId;
    private LocalDateTime dateTime;
    private String status;

}
