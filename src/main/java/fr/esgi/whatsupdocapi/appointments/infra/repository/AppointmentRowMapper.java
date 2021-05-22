package fr.esgi.whatsupdocapi.appointments.infra.repository;

import fr.esgi.whatsupdocapi.appointments.model.Appointment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AppointmentRowMapper implements RowMapper<Appointment> {
    @Override
    public Appointment mapRow(ResultSet resultSet, int i) throws SQLException {
        return Appointment.builder()
                .id(resultSet.getInt("id"))
                .id_doctor(resultSet.getInt("id_doctor"))
                .id_patient(resultSet.getInt("id_patient"))
                .date(resultSet.getTimestamp("date").toLocalDateTime())
                .status(resultSet.getString("status"))
                .build();
    }
}
