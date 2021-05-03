package fr.esgi.whatsupdocapi.symptoms.infra.web.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SymptomResponse {
    private int id;
    private String name;
}
