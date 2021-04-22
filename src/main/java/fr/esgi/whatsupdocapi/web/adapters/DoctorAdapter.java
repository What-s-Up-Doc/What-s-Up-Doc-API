package fr.esgi.whatsupdocapi.web.adapters;

import fr.esgi.whatsupdocapi.models.Doctor;
import org.springframework.stereotype.Component;
import fr.esgi.whatsupdocapi.web.responses.DoctorResponse;

@Component
public
class DoctorAdapter {
    
    public DoctorResponse map(Doctor doctor) {
        return new DoctorResponse()
                .setId(doctor.getId())
                .setFirstname(doctor.getFirstname())
                .setLastname(doctor.getLastname())
                .setEmail(doctor.getEmail())
                .setPassword(doctor.getPassword())
                .setPhone(doctor.getPhone())
                .setGender(doctor.getGender())
                .setSpeciality(doctor.getSpeciality());
    }
}