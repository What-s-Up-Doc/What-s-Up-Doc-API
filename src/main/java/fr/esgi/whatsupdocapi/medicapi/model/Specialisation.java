package fr.esgi.whatsupdocapi.medicapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Specialisation {
    private int id;
    private String name;
    private int specialistId;
}
