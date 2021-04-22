package fr.esgi.whatsupdocapi.doctors.infra.web.adapter;

import fr.esgi.whatsupdocapi.doctors.infra.web.response.DoctorResponse;
import org.springframework.stereotype.Component;
import fr.esgi.whatsupdocapi.doctors.model.Doctor;

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