package fr.esgi.whatsupdocapi.symptoms.infra.web.adapter;

import fr.esgi.whatsupdocapi.medicapi.model.Symptom;
import fr.esgi.whatsupdocapi.symptoms.infra.web.response.SymptomResponse;
import org.springframework.stereotype.Component;

@Component
public class SymptomAdapter {

    public SymptomResponse map(Symptom symptom) {
        return new SymptomResponse()
                .setName(symptom.getName())
                .setId(symptom.getId());

    }
}
