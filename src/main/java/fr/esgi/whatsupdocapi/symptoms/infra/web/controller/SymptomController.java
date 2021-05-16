package fr.esgi.whatsupdocapi.symptoms.infra.web.controller;

import fr.esgi.whatsupdocapi.medicapi.MedicApiService;
import fr.esgi.whatsupdocapi.medicapi.model.Gender;
import fr.esgi.whatsupdocapi.symptoms.infra.web.adapter.SymptomAdapter;
import fr.esgi.whatsupdocapi.symptoms.infra.web.request.SymptomRequest;
import fr.esgi.whatsupdocapi.symptoms.infra.web.response.SymptomResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Data
@RequestMapping("/api/symptom")
@RequiredArgsConstructor
public class SymptomController {
    private final MedicApiService medicApiService;
    private final SymptomAdapter symptomAdapter;

    @GetMapping
    public ResponseEntity<List<SymptomResponse>> getAllSymptomsForGenderAndBirthYear(SymptomRequest request) throws IOException, URISyntaxException {
        // todo check if birthyear is a valid  number
        Gender g = Gender.findByKey(request.getGender());
        // TODO check if gender is ok

        List<SymptomResponse> patients = medicApiService.getSymptoms(g, Integer.parseInt(request.getYearOfBirth()))
                .stream()
                .map(symptomAdapter::map)
                .collect(toList());

        return ok(patients);
    }

}
