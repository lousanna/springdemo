package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lousanna on 5/29/17.
 */

@Service
public interface DService {

    Person addPerson(String f, String l);
    String getAll();

    String getFirstName(String last);
    List<Person> getAllPeople();
}
