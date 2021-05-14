package fr.esgi.whatsupdocapi.patients.service;

import fr.esgi.whatsupdocapi.patients.infra.repository.JdbcPatientRepository;
import fr.esgi.whatsupdocapi.patients.model.Patient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final JdbcPatientRepository patientRepository;

    public int addPatient(String firstname, String lastname, String email,
                          String password, String phone, String gender, String birthday,
                          int isSmoker, double height, double weight,
                          String medical_history, String family_medical_history, String traitement) {
        var patientId = patientRepository.store(firstname,
                lastname, email, password, phone, gender,
                birthday, isSmoker, height, weight, medical_history,
                family_medical_history, traitement);
        log.info("Stored {}", lastname);
        return patientId;
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findOne(int patientId) {
        return patientRepository.findOne(patientId);
    }

    public void deleteOne(int patientId) {
        patientRepository.deleteOne(patientId);
    }

    public void modify(int id, String firstname, String lastname, String email,
                       String password, String phone, String gender, String birthday,
                       int isSmoker, double height, double weight,
                       String medical_history, String family_medical_history, String traitement) {
        patientRepository.modify(id, firstname, lastname, email, password, phone,
                gender, birthday, isSmoker, height, weight, medical_history,
                family_medical_history, traitement);
    }

    public Patient findPatientByEmail(String email) {
        return patientRepository.findOneFromEmail(email);
    }
}
