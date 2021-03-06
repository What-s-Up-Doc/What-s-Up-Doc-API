package fr.esgi.whatsupdocapi.patients.infra.repository;

import fr.esgi.whatsupdocapi.patients.model.Patient;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
class PatientRowMapper implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patient patient = new Patient();
        patient.setId(rs.getInt("id"));
        patient.setFirstname(rs.getString("firstname"));
        patient.setLastname(rs.getString("lastname"));
        patient.setPhone(rs.getString("phone"));
        patient.setGender(rs.getString("gender"));
        patient.setBirthday(rs.getString("birthday"));
        patient.setSmoker(rs.getInt("smoker"));
        patient.setHeight(rs.getDouble("height"));
        patient.setWeight(rs.getDouble("weight"));
        patient.setMedical_history(rs.getString("medical_history"));
        patient.setFamily_medical_history(rs.getString("family_medical_history"));
        patient.setTreatment(rs.getString("treatment"));
        patient.setAccountId(rs.getInt("id_account"));
        return patient;
    }
}
