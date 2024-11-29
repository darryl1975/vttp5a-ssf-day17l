package sg.edu.nus.iss.vttp5a_ssf_day17l.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.nus.iss.vttp5a_ssf_day17l.model.Joke;
import sg.edu.nus.iss.vttp5a_ssf_day17l.repository.ListRepo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping(path = {"/jokes"})
public class JokeController {
    
    @Autowired
    ListRepo listRepo;

    @GetMapping
    public String createJokePage() {
        return "jokecreate";
    }
    
    @PostMapping
    public String handleCreateJoke(@RequestBody MultiValueMap<String, String> formEntity, Model model) {

        Integer id = Integer.parseInt(formEntity.getFirst("id"));
        String type = formEntity.getFirst("type");
        String setup = formEntity.getFirst("setup");
        String punchline = formEntity.getFirst("punchline");
        Joke j = new Joke(id, type, setup, punchline);

        listRepo.rightPush("jokes", j.toString());

        model.addAttribute("joke", j);
        return "jokedisplay";
    }

    @GetMapping("/list")
    public String jokesList(Model model) {

        List<String> jokesString = listRepo.getList("jokes");

        // do your processing here
        List<Joke> jokes = new ArrayList<>();
        for(String s: jokesString) {
            String [] splitData = s.split(",");
            Joke j = new Joke(Integer.parseInt(splitData[0]), splitData[1], splitData[2], splitData[3]);
            jokes.add(j);
        }
        model.addAttribute("jokes", jokes);

        return "jokelist";
    }
    
    


}
