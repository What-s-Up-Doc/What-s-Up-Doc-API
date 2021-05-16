package fr.esgi.whatsupdocapi.appointments.service;

import fr.esgi.whatsupdocapi.appointments.model.Appointment;
import fr.esgi.whatsupdocapi.appointments.model.AppointmentRepository;
import fr.esgi.whatsupdocapi.core.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public Integer createAppointment(Integer doctorId, Integer patientId, LocalDateTime date, String status) {
        Appointment appointment = Appointment.builder()
                .id_doctor(doctorId)
                .id_patient(patientId)
                .date(date)
                .status(status)
                .build();

        return appointmentRepository.createAppointment(appointment).orElseThrow(() -> {
            throw new BadRequestException("Failed to store your appointment");
        }).getId();
    }
}