package fr.esgi.whatsupdocapi.doctors.infra.repository;

import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import fr.esgi.whatsupdocapi.doctors.model.DoctorRepository;
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
public class JdbcDoctorRepository implements DoctorRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final DoctorRowMapper mapper;


    @Override
    public int store(String firstname, String lastname, String email, String password, String phone, String gender, String speciality) {
        jdbcTemplate.update("INSERT INTO doctor (id, firstname, lastname, email, password, phone, gender, speciality) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", null, firstname, lastname, email, password, phone, gender, speciality);
        int id = findOneFromEmail(email).get().getId();
        return id;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from doctor", Integer.class);
    }

    @Override
    public List<Doctor> findAll() {
        return jdbcTemplate.query("select * from doctor", mapper);
    }

    @Override
    public Optional<Doctor> findOne(int doctorId) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("select * from doctor where id = ?", mapper, new Object[]{ doctorId })
        );
    }

    public Optional<Doctor> findOneFromEmail(String email) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("select * from doctor where email = ?", mapper, new Object[]{ email })
        );
    }

    @Override
    public void deleteOne(int doctorId) {
        String SQL = "DELETE FROM doctor WHERE id = ?";
        jdbcTemplate.update(SQL, doctorId);
    }

    @Override
    public void modify(int id, String firstname, String lastname, String email, String password, String phone, String gender, String speciality) {
        String SQL = "Update doctor set firstname = ?, lastname = ?, email = ?, password = ?, phone = ?, gender = ?, speciality = ? where id = ?";
        jdbcTemplate.update(SQL, firstname, lastname, email, password, phone, gender, speciality, id);
    }


}
