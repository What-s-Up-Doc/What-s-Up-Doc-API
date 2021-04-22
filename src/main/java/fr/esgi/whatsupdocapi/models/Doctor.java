package fr.esgi.whatsupdocapi.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Doctor extends User {
    private String speciality;
}
