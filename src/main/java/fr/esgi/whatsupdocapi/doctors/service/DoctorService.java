package fr.esgi.whatsupdocapi.doctors.service;

import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import fr.esgi.whatsupdocapi.doctors.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public String addDoctor(String firstname, String lastname, String email, String password, String phone, String gender, String speciality) {
        var doctorId = doctorRepository.store(firstname, lastname, email, password, phone, gender, speciality);
        log.info("Stored {}", lastname);
        return doctorId;
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> findOne(String userId) {
        return doctorRepository.findOne(userId);
    }

    public void deleteOne(String userId) {
        doctorRepository.deleteOne(userId);
    }


}
