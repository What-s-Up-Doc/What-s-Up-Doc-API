package fr.esgi.whatsupdocapi.diagnosis.infra.web.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class DiagnosisResponse {
    private List<Issue> issues;
}
