package fr.esgi.whatsupdocapi.doctors.infra.web.controller;

import fr.esgi.whatsupdocapi.core.exceptions.NotFoundException;
import fr.esgi.whatsupdocapi.doctors.infra.web.adapter.DoctorAdapter;
import fr.esgi.whatsupdocapi.doctors.infra.web.response.DoctorMinimalResponse;
import fr.esgi.whatsupdocapi.doctors.infra.web.response.DoctorResponse;
import fr.esgi.whatsupdocapi.doctors.service.DoctorService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

        if(doctors.isEmpty()) return ResponseEntity.noContent().build();
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
            @PathVariable("id") int doctorId
    ) {
        return doctorService.findOne(doctorId)
                .map(doctorAdapter::map)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {throw new NotFoundException("No doctors for this id");});
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDoctor(@PathVariable("id") int doctorId) {
        doctorService.deleteOne(doctorId);
    }

}
