package fr.esgi.whatsupdocapi.patients.infra.web.adapter;

import fr.esgi.whatsupdocapi.patients.infra.web.response.PatientMinimalResponse;
import fr.esgi.whatsupdocapi.patients.infra.web.response.PatientResponse;
import fr.esgi.whatsupdocapi.patients.model.Patient;
import org.springframework.stereotype.Component;

@Component
public
class PatientAdapter {

    public PatientResponse map(Patient patient) {
        return new PatientResponse()
                .setId(patient.getId())
                .setFirstname(patient.getFirstname())
                .setLastname(patient.getLastname())
                .setPhone(patient.getPhone())
                .setGender(patient.getGender())
                .setBirthday(patient.getBirthday())
                .setSmoker(patient.getSmoker())
                .setHeight(patient.getHeight())
                .setWeight(patient.getWeight())
                .setMedical_history(patient.getMedical_history())
                .setFamily_medical_history(patient.getFamily_medical_history())
                .setTreatment(patient.getTreatment());
    }

    public PatientMinimalResponse mapMinimalResponse(Patient patient) {
        return new PatientMinimalResponse()
                .setId(patient.getId())
                .setFirstname(patient.getFirstname())
                .setLastname(patient.getLastname());
    }
}
