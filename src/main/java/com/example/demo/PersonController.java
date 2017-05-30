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
    public PersonController(DService pr) {
        this.ds = pr;
    }

    @GetMapping("/greeting")
    public String Form(Model model) {
        model.addAttribute("person", new Person());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String Submit(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", ds.addPerson(person.getFirstName(),person.getLastName()));
        model.addAttribute("all", ds.getAll());
        return "result";
    }

    public String test(Model model){
        model.addAttribute("people",ds.getAllPeople());
        //System.out.println("In controller: " + ds.getAllPeople().toString());
        return ds.getAllPeople().toString();
    }
}
