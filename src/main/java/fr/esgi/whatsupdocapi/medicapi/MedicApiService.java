package fr.esgi.whatsupdocapi.medicapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.whatsupdocapi.medicapi.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedicApiService {

    private static final String mainURL = "https://priaid-symptom-checker-v1.p.rapidapi.com/";
    private static final String KEY = "";
    private static final String LANGUAGE = "fr-fr";

    private ResponseBody getResponse(String urlSuffix, MedicRequest request) throws IOException, URISyntaxException {
        URL url = request.buildUrl(mainURL + urlSuffix);

        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", KEY)
                .addHeader("x-rapidapi-host", "priaid-symptom-checker-v1.p.rapidapi.com")
                .build();

        Response response = client.newCall(req).execute();
        return response.body();
    }

    public List<Diagnosis> getDiagnosis(Gender gender, int yearOfBirth, int[] symptoms) throws URISyntaxException, IOException {
        MedicRequest request = new MedicRequest(gender.getKey(), yearOfBirth, symptoms, LANGUAGE);
        ResponseBody responseBody = getResponse("diagnosis", request);
        if(responseBody != null){
            ObjectMapper objectMapper = new ObjectMapper();
            DiagnosisResponse diagnosisResponse = objectMapper.readValue(responseBody.string(), DiagnosisResponse.class);
            return diagnosisResponse.getResponse();
        }
        return null;

    }

    public List<Symptom> getSymptoms(Gender gender, int yearOfBirth) throws URISyntaxException, IOException {
        MedicRequest request = new MedicRequest(gender.getKey(), yearOfBirth, LANGUAGE);
        ResponseBody responseBody = getResponse("symptoms/proposed", request);

        if(responseBody != null){
            ObjectMapper objectMapper = new ObjectMapper();
            return Arrays.asList(objectMapper.readValue(responseBody.string(), Symptom[].class));
        }
        return null;

    }
}
