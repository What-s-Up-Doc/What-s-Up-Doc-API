package fr.esgi.whatsupdocapi.diagnosis.infra.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Issue {
    private int id;
    private String name;
}
