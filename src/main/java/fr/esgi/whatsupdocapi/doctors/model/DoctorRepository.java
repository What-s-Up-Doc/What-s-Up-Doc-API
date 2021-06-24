package fr.esgi.whatsupdocapi.doctors.model;

import fr.esgi.whatsupdocapi.doctors.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {

    int store(String firstname, String lastname, String phone, String gender, String speciality, int accountId);

    int count();

    List<Doctor> findAll();

    Optional<Doctor> findOne(int doctorId);

    void deleteOne(int doctorId);

    void modify(int id, String firstname, String lastname, String phone,
                String gender, String speciality);
}
