package fr.esgi.whatsupdocapi.medicapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Issue {
    private int id;
    private String name;
    private int accuracy;
    private String icd;
    private String icdName;
    private String profName;
    private int ranking;
}
