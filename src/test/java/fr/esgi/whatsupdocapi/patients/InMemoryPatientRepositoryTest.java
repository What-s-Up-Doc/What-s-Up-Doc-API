package fr.esgi.whatsupdocapi.patients;


import fr.esgi.whatsupdocapi.patients.infra.repository.InMemoryPatientRepository;
import fr.esgi.whatsupdocapi.patients.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.*;


@SpringBootTest
public class InMemoryPatientRepositoryTest {

    @Autowired
    private InMemoryPatientRepository inMemoryPatientRepository;


    @Test
    public void createDoctorTest(){
        int count = inMemoryPatientRepository.count();
        String patientId = inMemoryPatientRepository.store("firstname", "lastname",
                "email", "password", "phone", "female", "birthday",true,
                0, 0, "medical_history", "family_medical_history", "traitement");
        assertEquals(inMemoryPatientRepository.count(), count + 1);
        assertTrue(inMemoryPatientRepository.getPatientDb().containsKey(patientId));
    }

    @Test
    public void findDoctorTest(){
        String patientId = "3";
        Patient patient = new Patient(patientId, "firstname", "lastname",
                "email", "password", "phone", "female", "birthday",true,
                0, 0, "medical_history", "family_medical_history", "traitement");
        inMemoryPatientRepository.getPatientDb().put(patientId, patient);
        int count = inMemoryPatientRepository.count();
        Optional<Patient> patientFind = inMemoryPatientRepository.findOne(patientId);
        assertEquals(inMemoryPatientRepository.count(), count);
        assertEquals(patientFind.get(), patient);
    }

    @Test
    public void modifyDoctorTest(){
        String patientId = "2";
        inMemoryPatientRepository.getPatientDb().put(patientId, new Patient(patientId, "firstname", "lastname",
                "email", "password", "phone", "female", "birthday",true,
                0, 0, "medical_history", "family_medical_history", "traitement"));
        int count = inMemoryPatientRepository.count();
        inMemoryPatientRepository.modify(patientId, "ChangedFirstname", "ChangedLastname",
                "ChangedEmail", "ChangedPassword", "ChangedPhone", "ChangeFemale", "ChangeBirthday",true,
                0, 0, "ChangeMedical_history", "ChangeFamily_medical_history", "ChangeTraitement");
        assertEquals(inMemoryPatientRepository.count(), count);
        System.out.println(inMemoryPatientRepository.getPatientDb());
        assertTrue(inMemoryPatientRepository.getPatientDb().containsKey(patientId));
        assertEquals(inMemoryPatientRepository.getPatientDb().get(patientId).toString(), new Patient(patientId, "ChangedFirstname", "ChangedLastname",
                "ChangedEmail", "ChangedPassword", "ChangedPhone", "ChangeFemale", "ChangeBirthday",true,
                0, 0, "ChangeMedical_history", "ChangeFamily_medical_history", "ChangeTraitement").toString());

    }

    @Test
    public void deletedoctorTest(){
        String patientId = "1";
        inMemoryPatientRepository.getPatientDb().put(patientId, new Patient(patientId, "firstname", "lastname",
                "email", "password", "phone", "female", "birthday",true,
                0, 0, "medical_history", "family_medical_history", "traitement"));
        int count = inMemoryPatientRepository.count();
        inMemoryPatientRepository.deleteOne(patientId);
        assertEquals(inMemoryPatientRepository.count(), count -1);
        assertFalse(inMemoryPatientRepository.getPatientDb().containsKey(patientId));
    }


    @Test
    public void findAllDoctorTest(){
        inMemoryPatientRepository.getPatientDb().clear();
        assert(inMemoryPatientRepository.findAll().isEmpty());
        inMemoryPatientRepository.getPatientDb().put("4", new Patient("4", "firstname", "lastname",
                "email", "password", "phone", "female", "birthday",true,
                0, 0, "medical_history", "family_medical_history", "traitement"));
        inMemoryPatientRepository.getPatientDb().put("5", new Patient("5", "firstname", "lastname",
                "email", "password", "phone", "female", "birthday",true,
                0, 0, "medical_history", "family_medical_history", "traitement"));
        assertEquals(inMemoryPatientRepository.findAll().size(), inMemoryPatientRepository.getPatientDb().size());

    }

}
