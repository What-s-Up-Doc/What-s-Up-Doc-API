package fr.esgi.whatsupdocapi.doctors;


import fr.esgi.whatsupdocapi.doctors.infra.repository.InMemoryDoctorRepository;
import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.*;


@SpringBootTest
public class InMemoryDoctorRepositoryTest {

    @Autowired
    private InMemoryDoctorRepository inMemoryDoctorRepository;


    @Test
    public void createDoctorTest(){
        int count = inMemoryDoctorRepository.count();
        String doctorId = inMemoryDoctorRepository.store("firstname", "lastname",
                "email", "password", "phone", "female", "speciality");
        assertEquals(inMemoryDoctorRepository.count(), count + 1);
        assertTrue(inMemoryDoctorRepository.getDoctorDb().containsKey(doctorId));
    }

    @Test
    public void findDoctorTest(){
        String doctorId = "3";
        Doctor doctor = new Doctor(doctorId, "firstname", "lastname",
                "email", "password", "phone", "male", "speciality");
        inMemoryDoctorRepository.getDoctorDb().put(doctorId, doctor);
        int count = inMemoryDoctorRepository.count();
        Optional<Doctor> doctorFind = inMemoryDoctorRepository.findOne(doctorId);
        assertEquals(inMemoryDoctorRepository.count(), count);
        assertEquals(doctorFind.get(), doctor);
    }

    @Test
    public void modifyDoctorTest(){
        String doctorId = "2";
        inMemoryDoctorRepository.getDoctorDb().put(doctorId, new Doctor(doctorId, "firstname", "lastname",
                "email", "password", "phone", "male", "speciality"));
        int count = inMemoryDoctorRepository.count();
        inMemoryDoctorRepository.modify(doctorId, "ChangedFirstname", "ChangedLastname",
                "ChangedEmail", "ChangedPassword", "ChangedPhone", "ChangeFemale", "ChangedSpeciality");
        assertEquals(inMemoryDoctorRepository.count(), count);
        System.out.println(inMemoryDoctorRepository.getDoctorDb());
        assertTrue(inMemoryDoctorRepository.getDoctorDb().containsKey(doctorId));
        assertEquals(inMemoryDoctorRepository.getDoctorDb().get(doctorId).toString(), new Doctor(doctorId, "ChangedFirstname", "ChangedLastname",
                "ChangedEmail", "ChangedPassword", "ChangedPhone", "ChangeFemale", "ChangedSpeciality").toString());

    }

    @Test
    public void deletedoctorTest(){
        String doctorId = "1";
        inMemoryDoctorRepository.getDoctorDb().put(doctorId, new Doctor(doctorId, "firstname", "lastname",
                "email", "password", "phone", "male", "speciality"));
        int count = inMemoryDoctorRepository.count();
        inMemoryDoctorRepository.deleteOne(doctorId);
        assertEquals(inMemoryDoctorRepository.count(), count -1);
        assertFalse(inMemoryDoctorRepository.getDoctorDb().containsKey(doctorId));
    }


    @Test
    public void findAllDoctorTest(){
        inMemoryDoctorRepository.getDoctorDb().clear();
        assert(inMemoryDoctorRepository.findAll().isEmpty());
        inMemoryDoctorRepository.getDoctorDb().put("4", new Doctor("4", "firstname", "lastname",
                "email", "password", "phone", "male", "speciality"));
        inMemoryDoctorRepository.getDoctorDb().put("5", new Doctor("5", "firstname", "lastname",
                "email", "password", "phone", "male", "speciality"));
        assertEquals(inMemoryDoctorRepository.findAll().size(), inMemoryDoctorRepository.getDoctorDb().size());

    }

}
