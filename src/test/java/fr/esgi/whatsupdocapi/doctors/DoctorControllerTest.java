package fr.esgi.whatsupdocapi.doctors;

import fr.esgi.whatsupdocapi.doctors.infra.web.adapter.DoctorAdapter;
import fr.esgi.whatsupdocapi.doctors.infra.web.controller.DoctorController;
import fr.esgi.whatsupdocapi.doctors.infra.web.exception.DoctorNotFoundException;
import fr.esgi.whatsupdocapi.doctors.infra.web.request.CreateDoctorRequest;
import fr.esgi.whatsupdocapi.doctors.infra.web.request.ModifyDoctorRequest;
import fr.esgi.whatsupdocapi.doctors.service.DoctorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DoctorControllerTest {

    @InjectMocks
    DoctorController doctorController;

    @Mock
    DoctorService doctorService;

    @Mock
    DoctorAdapter doctorAdapter;

    @Test
    public void shouldCallFindAllDoctor()throws Exception {
        doctorController.getAll();
        verify(doctorService, times(1)).findAll();
    }

    @Test(expected = DoctorNotFoundException.class)
    public void ShouldCallFindDoctor()throws Exception {
        int doctorId = 1;
        doctorController.findById(doctorId);
        verify(doctorService, times(1)).findOne(doctorId);
    }

    @Test
    public void shouldCallDeleteDoctor()throws Exception{
        int doctorId = 1;
        doctorController.deleteDoctor(doctorId);
        verify(doctorService, times(1)).deleteOne(doctorId);
    }

    @Test
    public void shouldCallCreateDoctor()throws Exception{
        CreateDoctorRequest request = new CreateDoctorRequest("firstname", "lastname",
                "email", "password", "password", "phone", "female", "speciality");

        MockHttpServletRequest requestHttp = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(requestHttp));

        when(doctorService.addDoctor(any(), any(), any(), any(), any(), any(), any())).thenReturn(1);

        doctorController.createDoctor(request);
        verify(doctorService, times(1)).addDoctor(request.getFirstname(),
                request.getLastname(), request.getEmail(), request.getPassword(),
                request.getPhone(), request.getGender(), request.getSpeciality());
    }

    @Test
    public void shouldCallModifyDoctor()throws Exception{
        ModifyDoctorRequest request = new ModifyDoctorRequest(1, "firstname", "lastname",
                "ChangeEmail", "ChangePassword", "ChangePhone", "female", "speciality");

        doctorController.modifyDoctor(request);
        verify(doctorService, times(1)).modify(request.getId(),request.getFirstname(),
                request.getLastname(), request.getEmail(), request.getPassword(),
                request.getPhone(), request.getGender(), request.getSpeciality());
    }
}
