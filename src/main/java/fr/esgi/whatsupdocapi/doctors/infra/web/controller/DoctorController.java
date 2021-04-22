package fr.esgi.whatsupdocapi.doctors.infra.web.controller;

import fr.esgi.whatsupdocapi.doctors.infra.web.adapter.DoctorAdapter;
import fr.esgi.whatsupdocapi.doctors.infra.web.exception.IllegalArgumentsException;
import fr.esgi.whatsupdocapi.doctors.infra.web.exception.IllegalIdException;
import fr.esgi.whatsupdocapi.doctors.infra.web.request.CreateDoctorRequest;
import fr.esgi.whatsupdocapi.doctors.infra.web.response.DoctorResponse;
import fr.esgi.whatsupdocapi.doctors.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorAdapter doctorAdapter;

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAllUsers() {
        var doctors = doctorService.findAll()
                .stream()
                .map(doctorAdapter::map)
                .collect(toList());

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
        try{
            doctorId = doctorService.addDoctor(request.getFirstname(), request.getLastname(),
                    request.getEmail(), request.getPassword(), request.getPhone(), request.getGender(), request.getSpeciality());
        }catch (Exception e){
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

}
