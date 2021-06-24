package fr.esgi.whatsupdocapi.doctors.infra.repository;

import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
class DoctorRowMapper implements RowMapper<Doctor> {

    @Override
    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(rs.getInt("id"));
        doctor.setFirstname(rs.getString("firstname"));
        doctor.setLastname(rs.getString("lastname"));
        doctor.setPhone(rs.getString("phone"));
        doctor.setGender(rs.getString("gender"));
        doctor.setSpeciality(rs.getString("speciality"));
        doctor.setAccountId(rs.getInt("id_account"));
        return doctor;
    }
}
