package fr.esgi.whatsupdocapi.appointments.infra.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetMyAppointmentsRequest {
    private Integer doctorId;
    private Integer patientId;
}
