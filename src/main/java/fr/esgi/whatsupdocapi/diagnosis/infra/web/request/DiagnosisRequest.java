package fr.esgi.whatsupdocapi.diagnosis.infra.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DiagnosisRequest {
    private String gender;
    private String yearOfBirth;
    private int[] symptoms;
}
