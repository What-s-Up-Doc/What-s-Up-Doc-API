package fr.esgi.whatsupdocapi.appointments.infra.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateAppointmentRequest {
    private Integer id_doctor;
    private Integer id_patient;
    private String date;
    private String status;
}
