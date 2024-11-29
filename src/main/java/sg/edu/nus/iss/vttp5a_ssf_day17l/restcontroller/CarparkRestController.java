package sg.edu.nus.iss.vttp5a_ssf_day17l.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.vttp5a_ssf_day17l.model.Carpark;
import sg.edu.nus.iss.vttp5a_ssf_day17l.service.restcontroller.CarparkRestService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/carparks")
public class CarparkRestController {
    
    @Autowired
    CarparkRestService carparkService;

    @GetMapping("/rates")
    public ResponseEntity<List<Carpark>> getParkingRates() {
        List<Carpark> carparks = carparkService.getCarparkRates();
        return ResponseEntity.ok().body(carparks);
    }
     
}
