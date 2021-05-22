package fr.esgi.whatsupdocapi.appointments.service;

import fr.esgi.whatsupdocapi.appointments.model.Appointment;
import fr.esgi.whatsupdocapi.appointments.model.AppointmentRepository;
import fr.esgi.whatsupdocapi.core.exceptions.BadRequestException;
import fr.esgi.whatsupdocapi.core.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Integer modifyAppointment(Integer id, Integer doctorId, Integer patientId, LocalDateTime date, String status) {
        Appointment appointment = Appointment.builder()
                .id(id)
                .id_doctor(doctorId)
                .id_patient(patientId)
                .date(date)
                .status(status)
                .build();

        return appointmentRepository.modifyAppointment(appointment).orElseThrow(() -> {
            throw new BadRequestException("Failed to update your appointment");
        }).getId();
    }

    public List<Appointment> getDoctorsAppointment(Integer id) {
        return appointmentRepository.findAppointmentsForDoctor(id);
    }

    public List<Appointment> getPatientAppointment(Integer id) {
        return appointmentRepository.findAppointmentsForPatient(id);
    }

    public Optional<Appointment> findOne(Integer appointmentId) {
        return appointmentRepository.findAppointmentById(appointmentId);
    }

    public void deleteOne(Integer appointmentId) {
        this.findOne(appointmentId).orElseThrow(() -> {
            throw new NotFoundException(String.format("There is no appointment for the id : %d", appointmentId));
        });
        appointmentRepository.deleteById(appointmentId);
    }

}