package fr.esgi.whatsupdocapi.patients.infra.repository;

import fr.esgi.whatsupdocapi.patients.model.Patient;
import fr.esgi.whatsupdocapi.patients.model.PatientRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Getter
@RequiredArgsConstructor
public class JdbcPatientRepository implements PatientRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final PatientRowMapper mapper;

    @Override
    public int store(String firstname, String lastname, String phone, String gender, String birthday, int smoker, double height, double weight, String medical_history, String family_medical_history, String treatment, int accountId) {
        jdbcTemplate.update("INSERT INTO patient (id, firstname, lastname, phone, gender, birthday, smoker, height, weight, medical_history, family_medical_history, treatment, id_account) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", null, firstname, lastname, phone, gender, birthday, smoker, height, weight, medical_history, family_medical_history, treatment, accountId);
        return findPatientFromAccount(accountId).getId();
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from patient", Integer.class);
    }

    public Patient findPatientFromAccount(int accountId) {
        List<Patient> patients = jdbcTemplate.query("select * from patient where id_account = ?", mapper, new Object[]{ accountId });
        if (patients.isEmpty()) return null;
        return patients.get(0);
    }

    @Override
    public List<Patient> findAll() {
        return jdbcTemplate.query("select * from patient", mapper);
    }

    @Override
    public Optional<Patient> findOne(int patientId) {
        List<Patient> patients = jdbcTemplate.query("select * from patient where id = ?", mapper, new Object[]{patientId});
        if (patients.isEmpty()) {
            return Optional.ofNullable(null);
        }
        return Optional.of(patients.get(0));
    }

    @Override
    public void deleteOne(int patientId) {
        String SQL = "DELETE FROM patient WHERE id = ?";
        jdbcTemplate.update(SQL, patientId);
    }

    @Override
    public void modify(int id, String firstname, String lastname, String phone, String gender, String birthday, int smoker, double height, double weight, String medical_history, String family_medical_history, String treatment) {
        String SQL = "Update patient set firstname = ?, lastname = ?, phone = ?, gender = ?, birthday = ?, smoker = ?, height = ?, weight = ?, medical_history = ?, family_medical_history = ?, treatment = ? where id = ?";
        jdbcTemplate.update(SQL, firstname, lastname, phone, gender, birthday, smoker, height, weight, medical_history, family_medical_history, treatment, id);
    }

}
