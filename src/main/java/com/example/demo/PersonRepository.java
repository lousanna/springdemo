package com.example.demo;

/**
 * Created by lousanna on 5/29/17.
 */
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String lastName);

}
