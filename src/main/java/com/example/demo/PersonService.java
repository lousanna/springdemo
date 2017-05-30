package com.example.demo;

/**
 * Created by lousanna on 5/29/17.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements DService{

    private final PersonRepository repo;

    @Autowired
    public PersonService(PersonRepository pr) {
        this.repo = pr;
    }

    @Transactional
    public Person addPerson(String f, String l) {
        Person a = new Person(f, l);
        repo.save(a);
        return a;
    }

    public String getAll() {
        String toRet="";
        for (Person customer : repo.findAll()) {
            toRet = toRet + (customer.toString() + ", ");
        }
        return toRet;
    }

    public List<Person> getAllPeople() {
        List<Person> toRet = new ArrayList<>();
        for (Person b : repo.findAll()) {
            toRet.add(b);
        }
        return toRet;
    }

    public Person getPerson(Long id) {
        return repo.findOne(id);
    }

    public String getFirstName(String last) {
        String toRet = "";
        for (Person b : repo.findByLastName(last)) {
            toRet = toRet + b.firstName;
        }
        return toRet;
    }

    public List<Person> findByLastName(String last) {
        List<Person> toRet = new ArrayList<>();
        for (Person b : repo.findByLastName(last)) {
            toRet.add(b);
        }
        return toRet;
    }
}
