package com.dinamohs.webpage.system.jdbc.mysql;

import com.dinamohs.webpage.system.dto.Person;
import com.dinamohs.webpage.system.dto.PersonPK;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PersonMySQLTest {

    PersonMySQL personMySQL;

    @Before
    public void init() {
        personMySQL = new PersonMySQL();
    }

    @Test
    public void updatePersonTest() {
        final int id = 10;
        Person person = personMySQL.getByPrimaryKey(id);
        System.out.println("from " + person);

        Person newPersonData = new Person();
        newPersonData.setIdPerson(id);
        newPersonData.setFirstName("Ariana");
        newPersonData.setLastName("Candiotti");
        personMySQL.update(newPersonData.createPK(), newPersonData);

        Person actual = personMySQL.getByPrimaryKey(id);
        System.out.println("to " + actual);
        assertNotEquals(person.getFirstName(), actual.getFirstName());
    }

    @Test
    public void deleteTest() {
        int id = 16;
        personMySQL.delete(new PersonPK(id));
        assertNull(personMySQL.getByPrimaryKey(id));
    }
}