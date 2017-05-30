package com.example.demo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by lousanna on 5/25/17.
 */

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;
    public String firstName;
    public String lastName;

    protected Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "ID= '%d': '%s' '%s'",
                id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String f) {
        this.firstName = f;
    }

    public void setLastName(String l) {
        this.lastName = l;
    }
}
