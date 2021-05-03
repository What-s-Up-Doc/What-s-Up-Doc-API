package fr.esgi.whatsupdocapi.medicapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Issue {

    @JsonProperty("ID")
    private int id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Accuracy")
    private int accuracy;

    @JsonProperty("Icd")
    private String icd;

    @JsonProperty("IcdName")
    private String icdName;

    @JsonProperty("ProfName")
    private String profName;

    @JsonProperty("Ranking")
    private int ranking;
}
