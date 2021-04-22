package fr.esgi.whatsupdocapi.web.controllers;

import fr.esgi.whatsupdocapi.services.DoctorService;
import fr.esgi.whatsupdocapi.web.adapters.DoctorAdapter;
import fr.esgi.whatsupdocapi.web.requests.CreateDoctorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import fr.esgi.whatsupdocapi.web.responses.DoctorResponse;

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
            @PathVariable("id") String userId
    ) {
        return doctorService.findOne(userId)
                .map(doctorAdapter::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateDoctorRequest request) {
        //todo exception
        /*if ("none".equalsIgnoreCase(request.getFirstname())) {
            throw new IllegalNameException("Illegal name for none");
        }*/

        String userId = doctorService.addUser(request.getFirstname(), request.getLastname(),
                request.getEmail(), request.getPassword(), request.getPhone(), request.getGender(), request.getSpeciality());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


}
