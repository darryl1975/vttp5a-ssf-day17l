package sg.edu.nus.iss.vttp5a_ssf_day17l.service.restcontroller;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import jakarta.json.JsonReaderFactory;
import sg.edu.nus.iss.vttp5a_ssf_day17l.constant.Url;
import sg.edu.nus.iss.vttp5a_ssf_day17l.model.Joke;

@Service
public class JokesRestService {
    
    RestTemplate restTemplate = new RestTemplate();

    public List<Joke> getRandomTenJokes() {
        ResponseEntity<String> jokesRawData = restTemplate.getForEntity(Url.jokesUrl, String.class);

        String jokesData = jokesRawData.getBody();

        JsonReader jsonReader = Json.createReader(new StringReader(jokesData));
        JsonArray jsonArray = jsonReader.readArray();

        List<Joke> jokes = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); i++) {
            jokes.add(new Joke(jsonArray.get(i).asJsonObject().getInt("id"), jsonArray.get(i).asJsonObject().getString("type"), jsonArray.get(i).asJsonObject().getString("setup"), jsonArray.get(i).asJsonObject().getString("punchline")));
        }

        return jokes;
    }

}
