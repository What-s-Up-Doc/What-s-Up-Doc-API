package fr.esgi.whatsupdocapi.patients.infra.repository;

import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import fr.esgi.whatsupdocapi.patients.model.Patient;
import fr.esgi.whatsupdocapi.patients.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class JdbcPatientRepository implements PatientRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PatientRowMapper mapper;

    @Override
    public String store(String firstname, String lastname, String email, String password, String phone, String gender, String birthday, boolean smoker, double height, double weight, String medical_history, String family_medical_history, String traitement) {
        var id = UUID.randomUUID().toString();
        jdbcTemplate.update("INSERT INTO patient (id, firstname, lastname, email, password, phone, gender, birthday, smoker, height, weight, medical_history, family_medical_history, traitement) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                null, firstname, lastname, email, password, phone, gender, birthday, smoker, height, weight, medical_history, family_medical_history, traitement);
        return id;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from patient", Integer.class);
    }

    @Override
    public List<Patient> findAll() {
        return jdbcTemplate.query("select * from patient", mapper);
    }

    @Override
    public Optional<Patient> findOne(String patientId) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("select * from patient where id = ?", mapper, new Object[]{ patientId })
        );
    }

    @Override
    public void deleteOne(String patientId) {
        String SQL = "DELETE FROM patient WHERE id = ?";
        jdbcTemplate.update(SQL, patientId);
    }

    @Override
    public void modify(String id, String firstname, String lastname, String email, String password, String phone, String gender, String birthday, boolean smoker, double height, double weight, String medical_history, String family_medical_history, String traitement) {
        String SQL = "Update patient set firstname = ?, lastname = ?, email = ?, password = ?, phone = ?, gender = ?, speciality = ?, birthday = ?, smoker = ?, height = ?, weight = ?, medical_history = ?, family_medical_history = ?, traitement = ? where id = ?";
        jdbcTemplate.update(SQL, firstname, lastname, email, password, phone, gender, birthday, smoker, height, weight, medical_history, family_medical_history, traitement, id);
    }

}
