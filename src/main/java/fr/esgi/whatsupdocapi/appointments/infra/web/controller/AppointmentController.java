package fr.esgi.whatsupdocapi.appointments.infra.web.controller;

import fr.esgi.whatsupdocapi.appointments.infra.web.request.CreateAppointmentRequest;
import fr.esgi.whatsupdocapi.appointments.infra.web.response.AppointmentIdResponse;
import fr.esgi.whatsupdocapi.appointments.service.AppointmentService;
import fr.esgi.whatsupdocapi.core.exceptions.BadRequestException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentIdResponse> createAppointment(
            @RequestBody CreateAppointmentRequest request
            ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");

        try {
            appointmentService.createAppointment(request.getId_doctor(), request.getId_patient(),
                            LocalDateTime.parse(request.getDate(), formatter), request.getStatus());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new BadRequestException("Please check your request");
        }


    }




}

