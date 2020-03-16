package com.dinamohs.webpage.services.rules;

import com.dinamohs.webpage.system.dto.Person;

import java.util.List;

public interface PersonRules {

    List<Person> getAllPersons();

    Person getPersonById(Integer id);

    boolean deletePersons(List<Integer> codes);

    boolean savePerson(Person person);
}
