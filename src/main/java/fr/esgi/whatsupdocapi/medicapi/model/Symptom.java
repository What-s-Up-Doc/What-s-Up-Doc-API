package fr.esgi.whatsupdocapi.medicapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Symptom {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("ID")
    private int id;

}
