package fr.esgi.whatsupdocapi.services;

import fr.esgi.whatsupdocapi.models.Patient;
import fr.esgi.whatsupdocapi.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public String addUser(String firstname, String lastname, String email,
                          String password, String phone, String gender, String birthday,
                          boolean isSmoker, double height, double weight,
                          String medical_history, String family_medical_history, String traitement) {
        var userId = patientRepository.store(UUID.randomUUID().toString(), firstname,
                                            lastname, email, password, phone, gender,
                                            birthday, isSmoker, height, weight, medical_history,
                                            family_medical_history, traitement);
        log.info("Stored {}", lastname);
        return userId;
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    public Optional<Patient> findOne(String userId) {
        return patientRepository.findOne(userId);
    }





}
