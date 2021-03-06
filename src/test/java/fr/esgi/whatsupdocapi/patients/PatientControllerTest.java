package fr.esgi.whatsupdocapi.patients;

import fr.esgi.whatsupdocapi.core.exceptions.NotFoundException;
import fr.esgi.whatsupdocapi.patients.infra.web.adapter.PatientAdapter;
import fr.esgi.whatsupdocapi.patients.infra.web.controller.PatientController;
import fr.esgi.whatsupdocapi.patients.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PatientControllerTest {

    @InjectMocks
    PatientController patientController;

    @Mock
    PatientService patientService;


    @Test
    public void shouldCallFindAllPatient()throws Exception {
        patientController.getAllPatients();
        verify(patientService, times(1)).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void ShouldCallFindPatient()throws Exception {
        int patientId = 1;
        patientController.findById(patientId);
        verify(patientService, times(1)).findOne(patientId);
    }

    @Test
    public void shouldCallDeletePatient()throws Exception{
        int patientId = 1;
        patientController.deletePatient(patientId);
        verify(patientService, times(1)).deleteOne(patientId);
    }

    /*
    @Test
    public void shouldCallCreatePatient()throws Exception{
        CreatePatientRequest request = new CreatePatientRequest("firstname", "lastname",
                "email", "password", "password", "phone", "female", "birthday",1,
                0, 0, "medical_history", "family_medical_history", "traitement");

        MockHttpServletRequest requestHttp = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(requestHttp));

        when(patientService.addPatient(anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString(), anyInt(), anyDouble(), anyDouble(),
                anyString(), anyString(), anyString())).thenReturn(1);

        patientController.createPatient(request);
        verify(patientService, times(1)).addPatient(request.getFirstname(),
                request.getLastname(), request.getEmail(), request.getPassword(),
                request.getPhone(), request.getGender(), request.getBirthday(),
                request.getSmoker(), request.getHeight(), request.getWeight(),
                request.getMedical_history(), request.getFamily_medical_history(),
                request.getTreatment());
    }

    @Test
    public void shouldCallModifyPatient()throws Exception{
        ModifyPatientRequest request = new ModifyPatientRequest(1, "firstname", "lastname",
                "ChangeEmail", "ChangePassword", "ChangePhone", "female", "birthday",0,
                0, 0, "medical_history", "family_medical_history", "traitement");

        patientController.modifyDoctor(request);
        verify(patientService, times(1)).modify(request.getId(),request.getFirstname(),
                request.getLastname(), request.getEmail(), request.getPassword(),
                request.getPhone(), request.getGender(), request.getBirthday(),
                request.getSmoker(), request.getHeight(), request.getWeight(),
                request.getMedical_history(), request.getFamily_medical_history(),
                request.getTreatment());
    }*/
}
