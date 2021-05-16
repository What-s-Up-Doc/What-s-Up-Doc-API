package fr.esgi.whatsupdocapi.medicapi.model;

import lombok.AllArgsConstructor;
import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

@AllArgsConstructor
public class MedicRequest {
    private final String gender;
    private final int yearOfBirth;
    private final int[] symptoms;
    private final String language;

    public MedicRequest(String gender, int yearOfBirth, String language) {
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
        this.language = language;
        this.symptoms = new int[0];
    }

    public URL buildUrl(String url) throws URISyntaxException, MalformedURLException {
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder.addParameter("gender", gender);
        uriBuilder.addParameter("year_of_birth", String.valueOf(yearOfBirth));
        uriBuilder.addParameter("symptoms", Arrays.toString(symptoms));
        uriBuilder.addParameter("language", language);
        return uriBuilder.build().toURL();
    }
}
