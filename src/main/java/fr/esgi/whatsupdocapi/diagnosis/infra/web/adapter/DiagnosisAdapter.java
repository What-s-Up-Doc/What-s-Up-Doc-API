package fr.esgi.whatsupdocapi.diagnosis.infra.web.adapter;

import fr.esgi.whatsupdocapi.diagnosis.infra.web.response.DiagnosisResponse;
import fr.esgi.whatsupdocapi.diagnosis.infra.web.response.Issue;
import fr.esgi.whatsupdocapi.medicapi.model.Diagnosis;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DiagnosisAdapter {

    public DiagnosisResponse map(Diagnosis diagnosis) {
        List<Issue> issues = new ArrayList<>();
        issues.add(new Issue(diagnosis.getIssue().getId(), diagnosis.getIssue().getName()));
        return new DiagnosisResponse().setIssues(issues);
    }
}
