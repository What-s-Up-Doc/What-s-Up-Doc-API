package fr.esgi.whatsupdocapi.appointments.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Appointment {
    private Integer id;
    private Integer id_doctor;
    private Integer id_patient;
    private LocalDateTime date;
    private String status;
}
