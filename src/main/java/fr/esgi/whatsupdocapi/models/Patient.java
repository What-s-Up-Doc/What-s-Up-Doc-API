package fr.esgi.whatsupdocapi.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Patient extends User{
    private String birthday;
    private boolean smoker;
    private double height;
    private double weight;
    private String medical_history;
    private String family_medical_history;
    private String traitement;
}
