package fr.esgi.whatsupdocapi.patients.infra.web.controller;

import fr.esgi.whatsupdocapi.core.exceptions.NotFoundException;
import fr.esgi.whatsupdocapi.patients.infra.web.adapter.PatientAdapter;
import fr.esgi.whatsupdocapi.patients.infra.web.response.PatientMinimalResponse;
import fr.esgi.whatsupdocapi.patients.infra.web.response.PatientResponse;
import fr.esgi.whatsupdocapi.patients.service.PatientService;
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
                .orElseThrow(() -> {throw new NotFoundException("No patient for this ID");});
    }

    @DeleteMapping(path = "/{id}")
    public void deletePatient(@PathVariable("id") int patientId) {
            patientService.deleteOne(patientId);
    }


}
