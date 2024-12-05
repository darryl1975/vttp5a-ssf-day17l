package sg.edu.nus.iss.vttp5a_ssf_day17l.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_ssf_day17l.model.Person;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping(path = "/persons")
public class PersonController {
    
    @GetMapping("/home")
    public String personHome() {
        return "personhome";
    }
    
    @GetMapping("/create")
    public String createPage(Model model) {
        Person p = new Person();

        model.addAttribute("person", p);
        return "personcreate";
    }

    // day 18 - slide 8
    @GetMapping("/create2")
    public ModelAndView createPage2() {
        Person p = new Person();

        ModelAndView mav = new ModelAndView();

        mav.setViewName("personcreate");
        mav.addObject("person", p);

        return mav;
    }
    
    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("person") Person entity, BindingResult results, Model model) {
 
        System.out.println(entity.getDob());

        if (results.hasErrors())
            return "personcreate";
        
        return "redirect:/persons/list";
    }
    
    
}
