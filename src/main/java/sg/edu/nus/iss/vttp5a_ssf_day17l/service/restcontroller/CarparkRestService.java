package sg.edu.nus.iss.vttp5a_ssf_day17l.service.restcontroller;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.vttp5a_ssf_day17l.constant.Url;
import sg.edu.nus.iss.vttp5a_ssf_day17l.model.Carpark;

@Service
public class CarparkRestService {
    RestTemplate restTemplate = new RestTemplate();

    public List<Carpark> getCarparkRates() {
        String carParkRawData = restTemplate.getForObject(Url.carparkUrl, String.class);

        JsonReader jsonReader = Json.createReader(new StringReader(carParkRawData));
        JsonObject jsonObject = jsonReader.readObject();
        JsonArray jsonArray = jsonObject.getJsonObject("result").getJsonArray("records");

        List<Carpark> carparks = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); i++) {
            // JsonObjectBuilder jObj = Json.createObjectBuilder();
            // jObj.add("id", jsonArray.get(i).asJsonObject().getInt("_id"));
            // jObj.add("carpark", jsonArray.get(i).asJsonObject().getString("carpark"));
            // jObj.add("category", jsonArray.get(i).asJsonObject().getString("category"));
            // jObj.add("weekDayRate1", jsonArray.get(i).asJsonObject().getString("weekdays_rate_1"));
            // jObj.add("weekDayRate2", jsonArray.get(i).asJsonObject().getString("weekdays_rate_2"));
            // jObj.add("saturdayRate", jsonArray.get(i).asJsonObject().getString("saturday_rate"));            
            // jObj.add("sundayPHRate", jsonArray.get(i).asJsonObject().getString("sunday_publicholiday_rate")); 
            
            Carpark c = new Carpark(jsonArray.get(i).asJsonObject().getInt("_id"), jsonArray.get(i).asJsonObject().getString("carpark"), jsonArray.get(i).asJsonObject().getString("category"), jsonArray.get(i).asJsonObject().getString("weekdays_rate_1"), jsonArray.get(i).asJsonObject().getString("weekdays_rate_2"), jsonArray.get(i).asJsonObject().getString("saturday_rate"), jsonArray.get(i).asJsonObject().getString("sunday_publicholiday_rate"));
            carparks.add(c);
        }

        return carparks;
    }
}
