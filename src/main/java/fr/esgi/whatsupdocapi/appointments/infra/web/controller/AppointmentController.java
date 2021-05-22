package fr.esgi.whatsupdocapi.appointments.infra.web.controller;

import fr.esgi.whatsupdocapi.appointments.infra.web.adapter.AppointmentAdapter;
import fr.esgi.whatsupdocapi.appointments.infra.web.request.CreateAppointmentRequest;
import fr.esgi.whatsupdocapi.appointments.infra.web.request.GetMyAppointmentsRequest;
import fr.esgi.whatsupdocapi.appointments.infra.web.response.AppointmentDetailResponse;
import fr.esgi.whatsupdocapi.appointments.infra.web.response.AppointmentIdResponse;
import fr.esgi.whatsupdocapi.appointments.model.Appointment;
import fr.esgi.whatsupdocapi.appointments.service.AppointmentService;
import fr.esgi.whatsupdocapi.core.exceptions.BadRequestException;
import fr.esgi.whatsupdocapi.core.exceptions.NotFoundException;
import fr.esgi.whatsupdocapi.doctors.infra.web.controller.DoctorController;
import fr.esgi.whatsupdocapi.patients.infra.web.controller.PatientController;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
    private final AppointmentService appointmentService;
    private final AppointmentAdapter appointmentAdapter;

    @PostMapping
    public ResponseEntity<AppointmentIdResponse> createAppointment(
            @RequestBody CreateAppointmentRequest request
            ) {

        try {
            Integer createdAppointmentId = appointmentService.createAppointment(
                            request.getId_doctor(),
                            request.getId_patient(),
                            LocalDateTime.parse(request.getDate(), formatter),
                            request.getStatus());

            AppointmentIdResponse response = AppointmentIdResponse.builder()
                    .appointment_id(createdAppointmentId)
                    .build()
                    .add(linkTo(AppointmentController.class)
                            .slash(createdAppointmentId)
                            .withSelfRel());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new BadRequestException("Please check your request");
        }
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getMyAppointments(
            @RequestBody GetMyAppointmentsRequest request
            ) {
        List<Appointment> result;
        if (request.getDoctorId() != null) {
            result = appointmentService.getDoctorsAppointment(request.getDoctorId());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (request.getPatientId() != null ) {
            result = appointmentService.getPatientAppointment(request.getPatientId());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        throw new BadRequestException("Please define a doctorId or patientId");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDetailResponse> findById(
            @PathVariable("id") int appointmentID
    ) {
        AppointmentDetailResponse response =  appointmentService.findOne(appointmentID)
                .map(appointmentAdapter::map)
                .orElseThrow(() -> {
                    throw new NotFoundException(String.format("There is no appointment for the id : %d", appointmentID));
                });

        Link linkToDoctor = linkTo(DoctorController.class)
                .slash(response.getDoctorId())
                .withSelfRel();
        Link linkToPatient = linkTo(PatientController.class)
                .slash(response.getPatientId())
                .withSelfRel();

        response.add(linkToDoctor, linkToPatient);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAppointment(@PathVariable("id") Integer appointmentId) {
        appointmentService.deleteOne(appointmentId);
    }


}

