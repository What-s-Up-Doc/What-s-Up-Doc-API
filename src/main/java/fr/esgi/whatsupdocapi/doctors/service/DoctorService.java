package fr.esgi.whatsupdocapi.doctors.service;

import fr.esgi.whatsupdocapi.core.exceptions.NotFoundException;
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

    public int addDoctor(String firstname, String lastname, String phone, String gender, String speciality, int accountId) {
        var doctorId = doctorRepository.store(firstname, lastname, phone, gender, speciality, accountId);
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
        doctorRepository.findOne(doctorId).orElseThrow(() ->    {
            throw new NotFoundException("No doctors for this id");
        });
        doctorRepository.deleteOne(doctorId);
    }

    public void modify(int id, String firstname, String lastname, String phone, String gender, String speciality) {
        doctorRepository.modify(id, firstname, lastname, phone, gender, speciality);
    }

    public Doctor findDoctorFromAccount(int accountId){
        return doctorRepository.findDoctorFromAccount(accountId);
    }
}
