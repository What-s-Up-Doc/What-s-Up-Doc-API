package fr.esgi.whatsupdocapi.medicapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Diagnosis {

    @JsonProperty("Issue")
    private Issue issue;

    @JsonProperty("Specialisation")
    private List<Specialisation> specialisations;
}
