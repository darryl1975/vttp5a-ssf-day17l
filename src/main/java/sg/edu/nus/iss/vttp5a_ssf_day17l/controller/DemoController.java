package sg.edu.nus.iss.vttp5a_ssf_day17l.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @GetMapping()    
    public String displayDateTime(Model model) throws ParseException {

        String strDate = "2004-08-09 15:30:33";
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date testDate = sdf.parse(strDate);
        long epochMillisecondsDate = testDate.getTime();

        Date testDate2 = new Date(epochMillisecondsDate);

        model.addAttribute("date", testDate2);
        return "demo";
    }
    
}
