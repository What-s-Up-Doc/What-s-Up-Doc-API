package fr.esgi.whatsupdocapi.patients.infra.web.controller;

import fr.esgi.whatsupdocapi.patients.infra.web.adapter.PatientAdapter;
import fr.esgi.whatsupdocapi.patients.infra.web.exception.IllegalArgumentsException;
import fr.esgi.whatsupdocapi.patients.infra.web.exception.IllegalIdException;
import fr.esgi.whatsupdocapi.patients.infra.web.helper.PatientControllerHelper;
import fr.esgi.whatsupdocapi.patients.infra.web.request.CreatePatientRequest;
import fr.esgi.whatsupdocapi.patients.infra.web.request.ModifyPatientRequest;
import fr.esgi.whatsupdocapi.patients.infra.web.response.PatientIdResponse;
import fr.esgi.whatsupdocapi.patients.infra.web.response.PatientMinimalResponse;
import fr.esgi.whatsupdocapi.patients.infra.web.response.PatientResponse;
import fr.esgi.whatsupdocapi.patients.model.Patient;
import fr.esgi.whatsupdocapi.patients.service.PatientService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (patients.isEmpty()) return ResponseEntity.noContent().build();
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
            @PathVariable("id") int patientId
    ) {
        return patientService.findOne(patientId)
                .map(patientAdapter::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody CreatePatientRequest request) {
        int patientId;

        PatientControllerHelper.verifyPasswordValidity(request.getPassword(), request.getConfirmedPassword());
        PatientControllerHelper.verifyUniqueEmailInRepository(request.getEmail(), patientService.findPatientByEmail(request.getEmail()));
        try {
            patientId = patientService.addPatient(request.getFirstname(), request.getLastname(),
                    request.getEmail(), request.getPassword(), request.getPhone(), request.getGender(),
                    request.getBirthday(), request.isSmoker(), request.getHeight(), request.getWeight(),
                    request.getMedical_history(), request.getFamily_medical_history(), request.getTreatment());
        } catch (Exception e) {
            System.out.println(request);
            throw new IllegalArgumentsException("Illegal arguments for patient creation");
        }
        PatientIdResponse patientIdResponse = new PatientIdResponse();
        patientIdResponse.setId(patientId);
        return new ResponseEntity<>(patientIdResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePatient(@PathVariable("id") int patientId) {
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
                    request.getMedical_history(), request.getFamily_medical_history(), request.getTreatment());
        } catch (Exception e) {
            throw new IllegalArgumentsException("Illegal arguments for patient modification");
        }
        patientService.modify(patient.getId(), patient.getFirstname(), patient.getLastname(),
                patient.getEmail(), patient.getPassword(), patient.getPhone(), patient.getGender(),
                patient.getBirthday(), patient.isSmoker(), patient.getHeight(), patient.getWeight(),
                patient.getMedical_history(), patient.getFamily_medical_history(), patient.getTreatment());

    }


}
