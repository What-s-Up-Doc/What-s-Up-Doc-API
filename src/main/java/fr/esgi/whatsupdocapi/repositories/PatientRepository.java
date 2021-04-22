package fr.esgi.whatsupdocapi.repositories;

import fr.esgi.whatsupdocapi.models.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
        String store(String id, String firstname, String lastname, String email,
                     String password, String phone, String gender, String birthday,
                     boolean isSmoker, double height, double weight,
                     String medical_history, String family_medical_history, String traitement);
        List<Patient> findAll();
        Optional<Patient> findOne(String userId);
}
