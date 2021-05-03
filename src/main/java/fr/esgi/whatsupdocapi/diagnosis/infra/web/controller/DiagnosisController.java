package fr.esgi.whatsupdocapi.diagnosis.infra.web.controller;

import fr.esgi.whatsupdocapi.diagnosis.infra.web.adapter.DiagnosisAdapter;
import fr.esgi.whatsupdocapi.diagnosis.infra.web.request.DiagnosisRequest;
import fr.esgi.whatsupdocapi.diagnosis.infra.web.response.DiagnosisResponse;
import fr.esgi.whatsupdocapi.medicapi.MedicApiService;
import fr.esgi.whatsupdocapi.medicapi.model.Gender;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Data
@RequestMapping("/api/diagnosis")
@RequiredArgsConstructor
public class DiagnosisController {

    private final MedicApiService medicApiService;
    private final DiagnosisAdapter diagnosisAdapter;

    @PostMapping
    public ResponseEntity<List<DiagnosisResponse>> getDiagnosis(@RequestBody DiagnosisRequest request) throws IOException, URISyntaxException {
        // todo check if symptoms are not empty and valid
        // todo check if birthyear is a valid  number
        Gender g = Gender.findByKey(request.getGender());
        // TODO check if gender is ok

        List<DiagnosisResponse> diagnosis = medicApiService
                .getDiagnosis(g, Integer.parseInt(request.getYearOfBirth()),
                        request.getSymptoms())
                .stream()
                .map(diagnosisAdapter::map)
                .collect(toList());

        return ok(diagnosis);
    }

}
