package fr.esgi.whatsupdocapi.doctors.infra.repository;

import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import fr.esgi.whatsupdocapi.doctors.model.DoctorRepository;
import fr.esgi.whatsupdocapi.security.user.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Getter
@RequiredArgsConstructor
public class JdbcDoctorRepository implements DoctorRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final DoctorRowMapper mapper;


    @Override
    public int store(String firstname, String lastname, String phone, String gender, String speciality, int accountId) {
        jdbcTemplate.update("INSERT INTO doctor (id, firstname, lastname, phone, gender, speciality, id_account) VALUES (?, ?, ?, ?, ?, ?, ?)", null, firstname, lastname, phone, gender, speciality, accountId);
        return findDoctorFromAccount(accountId).getId();
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
        List<Doctor> doctors = jdbcTemplate.query("select * from doctor where id = ?", mapper, new Object[]{ doctorId });
        if(doctors.isEmpty()) {
            return Optional.ofNullable(null);
        }
        return Optional.of(doctors.get(0));
    }

    public Doctor findDoctorFromAccount(int accountId) {
        List<Doctor> doctors = jdbcTemplate.query("select * from doctor where accountId = ?", mapper, new Object[]{ accountId });
        if(doctors.isEmpty()) return null;
        return doctors.get(0);
    }

    @Override
    public void deleteOne(int doctorId) {
        String SQL = "DELETE FROM doctor WHERE id = ?";
        jdbcTemplate.update(SQL, doctorId);
    }

    @Override
    public void modify(int id, String firstname, String lastname, String phone, String gender, String speciality) {
        String SQL = "Update doctor set firstname = ?, lastname = ?, phone = ?, gender = ?, speciality = ? where id = ?";
        jdbcTemplate.update(SQL, firstname, lastname, phone, gender, speciality, id);
    }


}
