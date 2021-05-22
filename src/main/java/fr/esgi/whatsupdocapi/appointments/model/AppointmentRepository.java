package fr.esgi.whatsupdocapi.appointments.model;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {

    List<Appointment> findAppointmentsForPatient(Integer patientId);

    List<Appointment> findAppointmentsForDoctor(Integer doctorsId);

    Optional<Appointment> createAppointment(Appointment appointment);

    Optional<Appointment> findAppointmentById(Integer appointmentId);

    void deleteById(Integer appointmentId);

    Optional<Appointment> modifyAppointment(Appointment appointment);

}
