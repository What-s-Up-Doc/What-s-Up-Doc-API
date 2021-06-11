package fr.esgi.whatsupdocapi.accounts;

import fr.esgi.whatsupdocapi.doctors.infra.web.controller.DoctorController;
import fr.esgi.whatsupdocapi.patients.infra.web.controller.PatientController;
import fr.esgi.whatsupdocapi.security.user.controller.AccountController;
import fr.esgi.whatsupdocapi.security.user.request.CreateAccountDoctorRequest;
import fr.esgi.whatsupdocapi.security.user.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import fr.esgi.whatsupdocapi.security.user.request.CreateAccountPatientRequest;
import fr.esgi.whatsupdocapi.security.user.request.ModifyAccountDoctorRequest;
import fr.esgi.whatsupdocapi.security.user.request.ModifyAccountPatientRequest;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    DoctorController doctorController;

    @Mock
    PatientController patientController;

    @Mock
    AccountService accountService;

    @Test
    public void shouldCallCreatePatient()throws Exception{
        CreateAccountPatientRequest request = new CreateAccountPatientRequest("firstname", "lastname",
                "email", "password", "confirmedPassword",
               "phone", "female", "birthday", 1, 0, 0, "medical_history", "family_medical_history", "traitement");
        MockHttpServletRequest requestHttp = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(requestHttp));

        when(accountService.addPatient(anyString(), anyString(), anyString(), anyString(),
                anyString(), anyInt(), anyDouble(), anyDouble(),
                anyString(), anyString(), anyString(), anyInt())).thenReturn(1);

        accountController.createPatientAccount(request);
        verify(accountService, times(1)).addAccount(request.getEmail(),
                request.getPassword(), "PATIENT");

        verify(accountService, times(1)).addPatient(request.getFirstname(),
                request.getLastname(), request.getPhone(), request.getGender(), request.getBirthday(),
                request.getSmoker(), request.getHeight(), request.getWeight(),
                request.getMedical_history(), request.getFamily_medical_history(),
                request.getTreatment(), anyInt());
    }

    @Test
    public void shouldCallCreateDoctor()throws Exception{
        CreateAccountDoctorRequest request = new CreateAccountDoctorRequest("firstname", "lastname",
                "email", "password", "confirmedPassword",
                "phone", "female", "speciality");
        MockHttpServletRequest requestHttp = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(requestHttp));

        when(accountService.addDoctor(anyString(), anyString(), anyString(), anyString(),
                anyString(), anyInt())).thenReturn(1);

        accountController.createDoctorAccount(request);
        verify(accountService, times(1)).addAccount(request.getEmail(),
                request.getPassword(), "DOCTOR");

        verify(accountService, times(1)).addDoctor(request.getFirstname(),
                request.getLastname(), request.getPhone(), request.getGender(), request.getSpeciality(),
                anyInt());
    }

    @Test
    public void shouldCallModifyPatient()throws Exception{
        ModifyAccountPatientRequest request = new ModifyAccountPatientRequest(1,"firstname", "lastname",
                "email", "password","phone", "female",
                "birthday", 1, 0, 0, "medical_history", "family_medical_history", "traitement");
        MockHttpServletRequest requestHttp = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(requestHttp));

        accountController.modifyPatient(request);
        verify(accountService, times(1)).modifyAccount(request.getEmail(),
                request.getPassword());

        verify(accountService, times(1)).modifyPatient(request.getId(), request.getFirstname(),
                request.getLastname(), request.getPhone(), request.getGender(), request.getBirthday(),
                request.getSmoker(), request.getHeight(), request.getWeight(),
                request.getMedical_history(), request.getFamily_medical_history(),
                request.getTreatment());
    }

    @Test
    public void shouldCallModifyDoctor()throws Exception{
        ModifyAccountDoctorRequest request = new ModifyAccountDoctorRequest(1,"firstname", "lastname",
                "email", "password","phone", "female",
                "speciality");
        MockHttpServletRequest requestHttp = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(requestHttp));

        accountController.modifyDoctor(request);
        verify(accountService, times(1)).modifyAccount(request.getEmail(),
                request.getPassword());

        verify(accountService, times(1)).modifyDoctor(request.getId(), request.getFirstname(),
                request.getLastname(), request.getPhone(), request.getGender(),
                request.getSpeciality());
    }
}
