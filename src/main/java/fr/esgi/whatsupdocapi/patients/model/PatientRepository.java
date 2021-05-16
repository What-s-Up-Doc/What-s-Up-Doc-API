package fr.esgi.whatsupdocapi.patients.model;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    int store(String firstname, String lastname, String email,
              String password, String phone, String gender, String birthday,
              int isSmoker, double height, double weight,
              String medical_history, String family_medical_history, String traitement);

    int count();

    List<Patient> findAll();

    Optional<Patient> findOne(int patientId);

    void deleteOne(int patientId);

    void modify(int id, String firstname, String lastname, String email,
                String password, String phone, String gender, String birthday,
                int isSmoker, double height, double weight,
                String medical_history, String family_medical_history, String traitement);
}
