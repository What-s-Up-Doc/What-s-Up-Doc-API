package fr.esgi.whatsupdocapi.medicapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiagnosisResponse {
    private List<Diagnosis> response;
}
