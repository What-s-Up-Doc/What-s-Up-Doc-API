package fr.esgi.whatsupdocapi.patients.infra.web.controller;

import fr.esgi.whatsupdocapi.patients.infra.web.exception.IllegalArgumentsException;
import fr.esgi.whatsupdocapi.patients.infra.web.exception.IllegalIdException;
import fr.esgi.whatsupdocapi.patients.service.PatientService;
import fr.esgi.whatsupdocapi.patients.infra.web.adapter.PatientAdapter;
import fr.esgi.whatsupdocapi.patients.infra.web.request.CreatePatientRequest;
import fr.esgi.whatsupdocapi.patients.infra.web.response.PatientResponse;
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
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        var patients = patientService.findAll()
                .stream()
                .map(patientAdapter::map)
                .collect(toList());

        return ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> findById(
            @PathVariable("id") String patientId
    ) {
        return patientService.findOne(patientId)
                .map(patientAdapter::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody CreatePatientRequest request) {
        String patientId;
        try{
            patientId = patientService.addPatient(request.getFirstname(), request.getLastname(),
                    request.getEmail(), request.getPassword(), request.getPhone(), request.getGender(),
                    request.getBirthday(), request.isSmoker(), request.getHeight(), request.getWeight(),
                    request.getMedical_history(), request.getFamily_medical_history(), request.getTraitement());
        }catch (Exception e){
            throw new IllegalArgumentsException("Illegal arguments for patient creation");
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patientId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deletePatient(@PathVariable("id") String patientId) {
        try {
            patientService.deleteOne(patientId);
        } catch (Exception e) {
            throw new IllegalIdException("Patient Id non valide");
        }
    }


}
