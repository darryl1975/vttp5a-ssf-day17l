package sg.edu.nus.iss.vttp5a_ssf_day17l.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.nus.iss.vttp5a_ssf_day17l.constant.Constant;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    @Qualifier(Constant.template01)
    RedisTemplate<String, String> redisTemplate;

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

    @GetMapping("/test")
    @ResponseBody
    public List<String> test() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        redisTemplate.opsForHash().put("test", "map", list.toString());

        Object s = redisTemplate.opsForHash().get("test", "map");
        List<String> list2 = new ArrayList<>();
        String str = s.toString();
        str = str.replace("[", "");
        str = str.replace("]", "");
        str = str.replace(" ", "");
        String [] splitString = str.split(",");
        for(String sss: splitString) {
            list2.add(sss);
        }

        return list2;
    }

}
