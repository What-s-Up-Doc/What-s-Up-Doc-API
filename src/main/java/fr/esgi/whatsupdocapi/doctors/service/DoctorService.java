package fr.esgi.whatsupdocapi.doctors.service;

import fr.esgi.whatsupdocapi.doctors.infra.repository.JdbcDoctorRepository;
import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorService {

    private final JdbcDoctorRepository doctorRepository;

    public int addDoctor(String firstname, String lastname, String email, String password, String phone, String gender, String speciality) {
        var doctorId = doctorRepository.store(firstname, lastname, email, password, phone, gender, speciality);
        log.info("Stored {}", lastname);
        return doctorId;
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> findOne(int doctorId) {
        return doctorRepository.findOne(doctorId);
    }

    public void deleteOne(int doctorId) {
        doctorRepository.deleteOne(doctorId);
    }

    public void modify(int id, String firstname, String lastname, String email, String password, String phone, String gender, String speciality) {
        doctorRepository.modify(id, firstname, lastname, email, password, phone, gender, speciality);
    }

    public Doctor findDoctorByEmail(String email){
        return doctorRepository.findOneFromEmail(email);
    }
}
