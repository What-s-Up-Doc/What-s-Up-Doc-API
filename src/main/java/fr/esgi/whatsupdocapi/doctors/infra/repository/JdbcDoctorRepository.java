package fr.esgi.whatsupdocapi.doctors.infra.repository;

import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import fr.esgi.whatsupdocapi.doctors.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class JdbcDoctorRepository implements DoctorRepository {

    private final JdbcTemplate jdbcTemplate;
    private final DoctorRowMapper mapper;


    @Override
    public String store(String firstname, String lastname, String email, String password, String phone, String gender, String speciality) {
        var id = UUID.randomUUID().toString();
        jdbcTemplate.update("INSERT INTO doctors (id, firstname, lastname, email, password, phone, gender, speciality) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", id, firstname, lastname, email, password, phone, gender, speciality);
        return id;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from doctors", Integer.class);
    }

    @Override
    public List<Doctor> findAll() {
        return jdbcTemplate.query("select * from doctors", mapper);
    }

    @Override
    public Optional<Doctor> findOne(String doctorId) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("select * from doctors where id = ?", mapper, new Object[]{ doctorId })
        );
    }

    @Override
    public void deleteOne(String doctorId) {
        String SQL = "DELETE FROM doctors WHERE id = ?";
        jdbcTemplate.update(SQL, doctorId);
    }

    @Override
    public void modify(String id, String firstname, String lastname, String email, String password, String phone, String gender, String speciality) {
        String SQL = "Update doctors set firstname = ?, lastname = ?, email = ?, password = ?, phone = ?, gender = ?, speciality = ? where id = ?";
        jdbcTemplate.update(SQL, firstname, lastname, email, password, phone, gender, speciality, id);
    }


}
