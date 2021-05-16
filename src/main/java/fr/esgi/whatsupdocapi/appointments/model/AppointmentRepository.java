package fr.esgi.whatsupdocapi.appointments.model;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {

    List<Appointment> findAppointmentsForPatient(Integer patientId);

    List<Appointment> findAppointmentsForDoctor(Integer doctorsId);

    Optional<Appointment> createAppointment(Appointment appointment);

    void deleteById(Integer appointmentId);

    void modifyAppointment(Appointment appointment);

}
