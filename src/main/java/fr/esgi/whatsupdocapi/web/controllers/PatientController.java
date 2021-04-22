package fr.esgi.whatsupdocapi.web.controllers;

import fr.esgi.whatsupdocapi.services.PatientService;
import fr.esgi.whatsupdocapi.web.adapters.PatientAdapter;
import fr.esgi.whatsupdocapi.web.requests.CreatePatientRequest;
import fr.esgi.whatsupdocapi.web.responses.PatientResponse;
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
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final PatientAdapter patientAdapter;

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllUsers() {
        var patients = patientService.findAll()
                .stream()
                .map(patientAdapter::map)
                .collect(toList());

        return ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> findById(
            @PathVariable("id") String userId
    ) {
        return patientService.findOne(userId)
                .map(patientAdapter::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreatePatientRequest request) {
        //todo exception
        /*if ("none".equalsIgnoreCase(request.getFirstname())) {
            throw new IllegalNameException("Illegal name for none");
        }*/

        String userId = patientService.addUser(request.getFirstname(), request.getLastname(),
                request.getEmail(), request.getPassword(), request.getPhone(), request.getGender(),
                request.getBirthday(), request.isSmoker(), request.getHeight(), request.getWeight(),
                request.getMedical_history(), request.getFamily_medical_history(), request.getTraitement());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


}
