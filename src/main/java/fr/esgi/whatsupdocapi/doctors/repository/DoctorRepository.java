package fr.esgi.whatsupdocapi.doctors.repository;

import fr.esgi.whatsupdocapi.doctors.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {
    String store(String firstname, String lastname,
                 String email, String password, String phone,
                 String gender, String speciality);

    int count();

    List<Doctor> findAll();

    Optional<Doctor> findOne(String doctorId);

    void deleteOne(String doctorId);
}
