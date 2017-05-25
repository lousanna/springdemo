package com.example.demo;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lousanna on 5/25/17.
 */

@Controller
public class GreetingController implements CommandLineRunner{


    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Person());
        return "greeting";
    }
    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Person greeting, Model model) {

        addPerson(greeting.first,greeting.last);
        model.addAttribute("greeting", new Person(0, greeting.first, greeting.last));
        log.info(getAll());
        model.addAttribute("all", getAll());
        return "result";
    }

    public String getAll(){

        String sql = "SELECT * FROM beans";

        String people = new String();

        List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Person customer = new Person((Integer)(row.get("id")), (String)row.get("first_name"), (String)row.get("last_name"));
            people= people + (customer.toString() + ", ");
        }

        log.info("hello" + people);
        return people;
    }
/*
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="first", required=true, defaultValue="John") String name, @RequestParam(value="last", required=true, defaultValue="Doe") String lastname,Model model) {
        addPerson(name, lastname);

        log.info("Querying for records:");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM beans",
                (rs, rowNum) -> new Person(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(person -> log.info(person.toString() + "\n"));

        model.addAttribute("greeting", name + " " + lastname);
        return "greeting";
    }*/

    public void addPerson(String first, String last) {
        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList(first + " " + last).stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO beans(first_name, last_name) VALUES (?,?)", splitUpNames);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE beans IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE beans(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
    }

}
