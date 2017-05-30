package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by lousanna on 5/25/17.
 */

@Controller
public class PersonController {
    private DService ds;

    @Autowired
    //@Qualifier("dservice")
    public PersonController(DService pr) {
        this.ds = pr;
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("person", new Person());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", ds.addPerson(person.firstName,person.lastName));
        model.addAttribute("all", ds.getAll());
        return "result";
    }
}
