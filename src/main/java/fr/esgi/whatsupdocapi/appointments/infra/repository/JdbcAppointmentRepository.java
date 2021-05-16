package fr.esgi.whatsupdocapi.appointments.infra.repository;

import fr.esgi.whatsupdocapi.appointments.model.Appointment;
import fr.esgi.whatsupdocapi.appointments.model.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcAppointmentRepository implements AppointmentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final AppointmentRowMapper rowMapper;


    @Override
    public List<Appointment> findAppointmentsForPatient(Integer patientId) {
        return jdbcTemplate.query("SELECT * FROM appointment WHERE id_patient = ?", rowMapper, patientId);
    }

    @Override
    public List<Appointment> findAppointmentsForDoctor(Integer doctorsId) {
        return jdbcTemplate.query("SELECT * FROM appointment WHERE id_doctor = ?", rowMapper, doctorsId);
    }

    @Override
    public Optional<Appointment> createAppointment(Appointment appointment) {
        int res = jdbcTemplate.update("INSERT INTO appointment (id_doctor, id_patient, date, status) VALUES (?, ?, ?, ?)",
                appointment.getId_doctor(), appointment.getId_patient(), appointment.getDate(), appointment.getStatus());
        if (res != 1) {
            return Optional.empty();
        }
        return Optional.of(appointment);
    }

    @Override
    public void deleteById(Integer appointmentId) {

    }

    @Override
    public void modifyAppointment(Appointment appointment) {

    }
}
