package fr.esgi.whatsupdocapi.medicapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Diagnosis {
    private Issue issue;
    private List<Specialisation> specialisations;
}
