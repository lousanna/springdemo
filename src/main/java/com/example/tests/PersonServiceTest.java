package com.example.tests;

import com.example.demo.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lousanna on 5/30/17.
 */

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
public class PersonServiceTest {

    @Mock
    private DService clientService;

    @InjectMocks
    private PersonController personController;


    @Before
    public void before() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void callMocked() {

        List<Person> people = new ArrayList<Person>();
        final Model map = Mockito.mock(Model.class);

        Person one = new Person("hi", "he");
        one.setId(1L);
        Person two = new Person("ha", "ho");
        two.setId(2L);
        people.add(one);
        people.add(two);

        assertNotNull(clientService);
        assertNotNull(personController);

        Mockito.when(clientService.getAllPeople()).thenReturn(people);

        final String result = personController.test(map);

        Mockito.verify(map).addAttribute("people", people);
        assertNotNull(result);

        //System.out.println(clientService.getAllPeople() + "\n" + people.toString());
        assertEquals(people.toString(), result);
    }
}
