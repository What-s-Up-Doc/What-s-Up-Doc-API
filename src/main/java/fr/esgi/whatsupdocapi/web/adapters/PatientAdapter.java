package fr.esgi.whatsupdocapi.web.adapters;

import fr.esgi.whatsupdocapi.models.Patient;
import fr.esgi.whatsupdocapi.web.responses.PatientResponse;
import org.springframework.stereotype.Component;

@Component
public
class PatientAdapter {

    public PatientResponse map(Patient patient) {
        return new PatientResponse()
                .setId(patient.getId())
                .setFirstname(patient.getFirstname())
                .setLastname(patient.getLastname())
                .setEmail(patient.getEmail())
                .setPassword(patient.getPassword())
                .setPhone(patient.getPhone())
                .setGender(patient.getGender())
                .setBirthday(patient.getBirthday())
                .setSmoker(patient.isSmoker())
                .setHeight(patient.getHeight())
                .setWeight(patient.getWeight())
                .setMedical_history(patient.getMedical_history())
                .setFamily_medical_history(patient.getFamily_medical_history());

    }
}