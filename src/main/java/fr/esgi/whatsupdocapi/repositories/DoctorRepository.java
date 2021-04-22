package fr.esgi.whatsupdocapi.repositories;

import fr.esgi.whatsupdocapi.models.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {
    String store(String id, String firstname, String lastname,
                 String email, String password, String phone,
                 String gender, String speciality);
    List<Doctor> findAll();
    Optional<Doctor> findOne(String userId);
}
