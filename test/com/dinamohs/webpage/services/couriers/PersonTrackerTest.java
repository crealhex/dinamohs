package com.dinamohs.webpage.services.couriers;

import com.dinamohs.webpage.services.rules.PersonRules;
import com.dinamohs.webpage.system.dao.PersonDAO;
import com.dinamohs.webpage.system.dto.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTrackerTest {

    PersonRules personRules;

    @Before
    public void setUp() throws Exception {
        personRules = PersonTracker.getInstance();
    }

    @Test
    public void getAllPersons() {
        System.out.println(personRules.getAllPersons());
        assertNotNull(personRules.getAllPersons());
    }

    @Test
    public void savePersonTest() {
        Person person = new Person();
        person.setIdPerson(10);
        person.setFirstName("Luis");
        person.setLastName("Enco");
        assertTrue(personRules.savePerson(person));
    }

    @After
    public void tearDown() throws Exception {
    }
}