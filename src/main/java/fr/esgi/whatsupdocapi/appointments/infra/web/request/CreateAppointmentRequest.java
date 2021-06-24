package fr.esgi.whatsupdocapi.appointments.infra.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateAppointmentRequest {
    private Integer idDoctor;
    private Integer idPatient;
    private String date;
    private String status;
}
