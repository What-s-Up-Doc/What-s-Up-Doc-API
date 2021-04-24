package fr.esgi.whatsupdocapi.patients.infra.repository;

import fr.esgi.whatsupdocapi.patients.model.Patient;
import fr.esgi.whatsupdocapi.patients.repository.PatientRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryPatientRepository implements PatientRepository {

    private final Map<String, Patient> patientDb = new HashMap<>();

    @Override
    public String store(String firstname, String lastname, String email,
                        String password, String phone, String gender, String birthday,
                        boolean isSmoker, double height, double weight,
                        String medical_history, String family_medical_history, String traitement) {
        String patientId = UUID.randomUUID().toString();
        patientDb.put(patientId, new Patient(patientId, firstname, lastname, email,
                password, phone, gender, birthday, isSmoker, height, weight,
                medical_history, family_medical_history, traitement));
        return patientId;
    }

    @Override
    public int count() {
        return patientDb.size();
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(patientDb.values());
    }

    @Override
    public Optional<Patient> findOne(String patientId) {
        return Optional.ofNullable(patientDb.get(patientId));
    }

    @Override
    public void deleteOne(String patientId) {
        Patient patient = patientDb.get(patientId);
        if (Objects.nonNull(patient)) {
            patientDb.remove(patient);
        }
    }

    @Override
    public void modify(String patientId, String firstname, String lastname, String email, String password, String phone, String gender, String birthday,
                       boolean isSmoker, double height, double weight, String medical_history, String family_medical_history, String traitement) {
        Patient patient = patientDb.get(patientId);
        if (Objects.nonNull(patient)) {
            patientDb.replace(patientId, patient, new Patient(patientId, firstname, lastname, email, password,
                    phone, gender, birthday, isSmoker, height, weight,
                    medical_history, family_medical_history, traitement));
        }
    }
}
