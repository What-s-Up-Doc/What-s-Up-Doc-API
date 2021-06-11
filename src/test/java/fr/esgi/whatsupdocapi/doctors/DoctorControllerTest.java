package fr.esgi.whatsupdocapi.doctors;

import fr.esgi.whatsupdocapi.core.exceptions.NotFoundException;
import fr.esgi.whatsupdocapi.doctors.infra.web.adapter.DoctorAdapter;
import fr.esgi.whatsupdocapi.doctors.infra.web.controller.DoctorController;
import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import fr.esgi.whatsupdocapi.doctors.service.DoctorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DoctorControllerTest {

    @InjectMocks
    DoctorController doctorController;

    @Mock
    DoctorService doctorService;

    @Test
    public void shouldCallFindAllDoctor()throws Exception {
        doctorController.getAll();
        verify(doctorService, times(1)).findAll();
    }

    @Test(expected = NotFoundException.class)
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
}
