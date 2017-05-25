package com.example.demo;
import java.sql.Timestamp;
/**
 * Created by lousanna on 5/25/17.
 */
public class Person {
    public int id;
    public String first;
    public String last;

    public Person() {
    }

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.first = firstName;
        this.last = lastName;
    }

    public long getId(){
        return this.id;
    }
    public String getFirst() {
        return this.first;
    }
    public String getLast() {
        return this.last;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last){
        this.last = last;
    }

    public String toString() {
        return String.format(
                "ID= '%d': '%s' '%s'",
                id, first, last);
    }
}
