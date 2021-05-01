package fr.esgi.whatsupdocapi.doctors.infra.repository;


import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import fr.esgi.whatsupdocapi.doctors.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Getter
public class InMemoryDoctorRepository implements DoctorRepository {

    private final Map<String, Doctor> doctorDb = new HashMap<>();

    @Override
    public String store(String firstname, String lastname, String email, String password, String phone, String gender, String speciality) {
        String doctorId = UUID.randomUUID().toString();
        doctorDb.put(doctorId, new Doctor(doctorId, firstname, lastname, email, password, phone, gender, speciality));
        return doctorId;
    }

    @Override
    public int count() {
        return doctorDb.size();
    }

    @Override
    public List<Doctor> findAll() {
        return new ArrayList<>(doctorDb.values());
    }

    @Override
    public Optional<Doctor> findOne(String doctorId) {
        return Optional.ofNullable(doctorDb.get(doctorId));
    }

    @Override
    public void deleteOne(String doctorId) {
        doctorDb.remove(doctorId);
    }

    @Override
    public void modify(String doctorId, String firstname, String lastname, String email, String password, String phone, String gender, String speciality) {
        Doctor doctor = doctorDb.get(doctorId);
        if (Objects.nonNull(doctor)) {
            doctorDb.replace(doctorId, doctor, new Doctor(doctorId, firstname, lastname, email, password, phone, gender, speciality));
        }
    }

}
