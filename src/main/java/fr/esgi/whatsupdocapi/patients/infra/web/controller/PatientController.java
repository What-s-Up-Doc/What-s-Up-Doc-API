package fr.esgi.whatsupdocapi.patients.infra.web.controller;

import fr.esgi.whatsupdocapi.patients.infra.web.adapter.PatientAdapter;
import fr.esgi.whatsupdocapi.patients.infra.web.exception.IllegalArgumentsException;
import fr.esgi.whatsupdocapi.patients.infra.web.exception.IllegalIdException;
import fr.esgi.whatsupdocapi.patients.infra.web.request.CreatePatientRequest;
import fr.esgi.whatsupdocapi.patients.infra.web.request.ModifyPatientRequest;
import fr.esgi.whatsupdocapi.patients.infra.web.response.PatientMinimalResponse;
import fr.esgi.whatsupdocapi.patients.infra.web.response.PatientResponse;
import fr.esgi.whatsupdocapi.patients.model.Patient;
import fr.esgi.whatsupdocapi.patients.service.PatientService;
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
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final PatientAdapter patientAdapter;

    @GetMapping
    public ResponseEntity<List<PatientMinimalResponse>> getAllPatients() {
        List<PatientMinimalResponse> patients = patientService.findAll()
                .stream()
                .map(patientAdapter::mapMinimalResponse)
                .collect(toList());

        for (final PatientMinimalResponse patient : patients) {
            Link link = linkTo(methodOn(PatientController.class)
                    .findById(patient.getId()))
                    .withSelfRel();
            patient.add(link);
        }
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
        try {
            patientId = patientService.addPatient(request.getFirstname(), request.getLastname(),
                    request.getEmail(), request.getPassword(), request.getPhone(), request.getGender(),
                    request.getBirthday(), request.isSmoker(), request.getHeight(), request.getWeight(),
                    request.getMedical_history(), request.getFamily_medical_history(), request.getTraitement());
        } catch (Exception e) {
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

    @PutMapping
    public void modifyDoctor(@RequestBody ModifyPatientRequest request) {
        Patient patient;
        try {
            patient = new Patient(request.getId(), request.getFirstname(), request.getLastname(),
                    request.getEmail(), request.getPassword(), request.getPhone(), request.getGender(),
                    request.getBirthday(), request.isSmoker(), request.getHeight(), request.getWeight(),
                    request.getMedical_history(), request.getFamily_medical_history(), request.getTraitement());
        } catch (Exception e) {
            throw new IllegalArgumentsException("Illegal arguments for patient creation");
        }
        patientService.modify(patient.getId(), patient.getFirstname(), patient.getLastname(),
                patient.getEmail(), patient.getPassword(), patient.getPhone(), patient.getGender(),
                patient.getBirthday(), patient.isSmoker(), patient.getHeight(), patient.getWeight(),
                patient.getMedical_history(), patient.getFamily_medical_history(), patient.getTraitement());

    }


}
