package fr.esgi.whatsupdocapi.doctors.infra.web.controller;

import fr.esgi.whatsupdocapi.doctors.infra.web.adapter.DoctorAdapter;
import fr.esgi.whatsupdocapi.doctors.infra.web.exception.IllegalArgumentsException;
import fr.esgi.whatsupdocapi.doctors.infra.web.exception.IllegalIdException;
import fr.esgi.whatsupdocapi.doctors.infra.web.request.CreateDoctorRequest;
import fr.esgi.whatsupdocapi.doctors.infra.web.request.ModifyDoctorRequest;
import fr.esgi.whatsupdocapi.doctors.infra.web.response.DoctorMinimalResponse;
import fr.esgi.whatsupdocapi.doctors.infra.web.response.DoctorResponse;
import fr.esgi.whatsupdocapi.doctors.model.Doctor;
import fr.esgi.whatsupdocapi.doctors.service.DoctorService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Data
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorAdapter doctorAdapter;

    @GetMapping
    public ResponseEntity<List<DoctorMinimalResponse>> getAll() {
        List<DoctorMinimalResponse> doctors = doctorService.findAll()
                .stream()
                .map(doctorAdapter::mapMinimalResponse)
                .collect(toList());

        for (final DoctorMinimalResponse doctor : doctors) {
            Link link = linkTo(methodOn(DoctorController.class)
                    .findById(doctor.getId()))
                    .withSelfRel();
            doctor.add(link);
        }
        return ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> findById(
            @PathVariable("id") String doctorId
    ) {
        return doctorService.findOne(doctorId)
                .map(doctorAdapter::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createDoctor(@RequestBody CreateDoctorRequest request) {

        String doctorId;
        try {
            doctorId = doctorService.addDoctor(request.getFirstname(), request.getLastname(),
                    request.getEmail(), request.getPassword(), request.getPhone(), request.getGender(), request.getSpeciality());
        } catch (Exception e) {
            throw new IllegalArgumentsException("Illegal arguments for patient creation");
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(doctorId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDoctor(@PathVariable("id") String doctorId) {
        try {
            doctorService.deleteOne(doctorId);
        } catch (Exception e) {
            throw new IllegalIdException("Doctor Id non valide");
        }
    }

    @PutMapping
    public void modifyDoctor(@RequestBody ModifyDoctorRequest request) {
        Doctor doctor;
        try {
            doctor = new Doctor(request.getId(), request.getFirstname(), request.getLastname(),
                    request.getEmail(), request.getPassword(), request.getPhone(), request.getGender(), request.getSpeciality());
        } catch (Exception e) {
            throw new IllegalArgumentsException("Illegal arguments for doctor creation");
        }
        doctorService.modify(doctor.getId(), doctor.getFirstname(), doctor.getLastname(), doctor.getEmail(),
                doctor.getPassword(), doctor.getPhone(), doctor.getGender(), doctor.getSpeciality());

    }

}
