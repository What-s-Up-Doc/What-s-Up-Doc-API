package fr.esgi.whatsupdocapi.medicapi.model;

import lombok.Getter;

@Getter
public enum Gender {
    
    MALE("male"), FEMALE("female");
    
    private final String key;

    Gender(String key) {
        this.key = key;
    }

    public static Gender findByKey(String key){
        for(Gender g : values()){
            if(g.getKey().equals(key)){
                return g;
            }
        }
        return null;
    }
}
