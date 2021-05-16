package fr.esgi.whatsupdocapi.symptoms.infra.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SymptomRequest {
    private String gender;
    private String yearOfBirth;
}
