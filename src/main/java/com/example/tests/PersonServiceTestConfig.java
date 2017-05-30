package com.example.tests;

import com.example.demo.DService;
import com.example.demo.PersonService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by lousanna on 5/30/17.
 */

@Profile("test")
@Configuration
public class PersonServiceTestConfig {

    @Bean
    @Primary
    public DService personService() {
        return Mockito.mock(DService.class);
    }
}
