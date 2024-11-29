package sg.edu.nus.iss.vttp5a_ssf_day17l.service.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sg.edu.nus.iss.vttp5a_ssf_day17l.constant.Url;
import sg.edu.nus.iss.vttp5a_ssf_day17l.model.Carpark;

@Service
public class CarparkService {
    
    RestTemplate restTemplate = new RestTemplate();

    public List<Carpark> getCarparkList() {
        ResponseEntity<List<Carpark>> carparks = restTemplate.exchange(Url.localCarparkUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Carpark>>() {});

        return carparks.getBody();

    }
}
