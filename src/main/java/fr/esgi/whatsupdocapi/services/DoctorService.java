package fr.esgi.whatsupdocapi.services;

import fr.esgi.whatsupdocapi.models.Doctor;
import fr.esgi.whatsupdocapi.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public String addUser(String firstname, String lastname, String email, String password, String phone, String gender, String speciality) {
        var userId = doctorRepository.store(UUID.randomUUID().toString(), firstname, lastname, email, password, phone, gender, speciality);
        log.info("Stored {}", lastname);
        return userId;
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> findOne(String userId) {
        return doctorRepository.findOne(userId);
    }





}
