package ru.vmmb.java.examples.exampleweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class MainController {

    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("datetime",new Date());
        return "welcome";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
