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
    private final CreatedAppointmentRowMapper createdAppointmentRowMapper;



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
        int res = jdbcTemplate.queryForObject("INSERT INTO appointment (id_doctor, id_patient, date, status) VALUES (?, ?, ?, ?) RETURNING id", createdAppointmentRowMapper, appointment.getId_doctor(), appointment.getId_patient(), appointment.getDate(), appointment.getStatus());
        return this.findAppointmentById(res);
    }

    @Override
    public Optional<Appointment> findAppointmentById(Integer appointmentId) {
        List<Appointment> appointments = jdbcTemplate.query("SELECT * FROM appointment WHERE id = ?", rowMapper, appointmentId);

        if(appointments.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(appointments.get(0));
    }

    @Override
    public void deleteById(Integer appointmentId) {
        jdbcTemplate.update("DELETE FROM appointment WHERE id = ?", appointmentId);
    }

    @Override
    public Optional<Appointment> modifyAppointment(Appointment appointment) {
        jdbcTemplate.update("UPDATE appointment SET id_doctor = ?, id_patient = ?, date = ?, status = ? WHERE id = ?",
                appointment.getId_doctor(), appointment.getId_patient(), appointment.getDate(), appointment.getStatus(), appointment.getId());
        return this.findAppointmentById(appointment.getId());
    }
}
