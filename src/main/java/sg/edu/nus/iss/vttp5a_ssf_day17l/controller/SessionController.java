package sg.edu.nus.iss.vttp5a_ssf_day17l.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.vttp5a_ssf_day17l.model.SessionData;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/sessions")
public class SessionController {
    
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    public String showSessions(HttpSession httpSession, Model model) {

        List<SessionData> sessions = null;
        if (httpSession.getAttribute("session") == null) {
            sessions = new ArrayList<>();
        } else {
            sessions = (List<SessionData>) httpSession.getAttribute("session");
        }

        model.addAttribute("sessions", sessions);
        return "sessionlist";
    }

    @GetMapping("")
    public String sessionCreate(Model model) {
        SessionData sessionData = new SessionData();
        model.addAttribute("session", sessionData);
        return "sessioncreate";
    }

    @SuppressWarnings("unchecked")
    @PostMapping("")
    public String postSessionCreate(@ModelAttribute("session") SessionData entity, HttpSession httpSession, Model model) {

        List<SessionData> sessions = null;
        if (httpSession.getAttribute("session") == null) {
            sessions = new ArrayList<>();
        } else {
            sessions = (List<SessionData>) httpSession.getAttribute("session");
        }
        sessions.add(entity);
        
        httpSession.setAttribute("session", sessions);
        model.addAttribute("sessions", sessions);
        return "sessionlist";
    }
    
    @GetMapping("/clear")
    public String clearSession(HttpSession httpSession) {
        httpSession.removeAttribute("session");
        httpSession.invalidate();

        return "redirect:/sessions/home";
    }
    
    
    
}
