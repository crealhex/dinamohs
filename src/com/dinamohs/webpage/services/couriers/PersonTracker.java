package com.dinamohs.webpage.services.couriers;

import com.dinamohs.webpage.services.exceptions.BusinessException;
import com.dinamohs.webpage.services.rules.PersonRules;
import com.dinamohs.webpage.system.dao.PersonDAO;
import com.dinamohs.webpage.system.dto.Person;
import com.dinamohs.webpage.system.dto.PersonPK;
import com.dinamohs.webpage.system.exceptions.PersonDaoException;
import com.dinamohs.webpage.system.factory.PersonDaoFactory;

import java.util.Arrays;
import java.util.List;

public class PersonTracker implements PersonRules {

    private static PersonTracker personTracker;
    PersonDAO personDAO;

    private PersonTracker() {}

    public static PersonTracker getInstance() {
        if (personTracker == null) {
            personTracker = new PersonTracker();
        }
        return personTracker;
    }

    @Override
    public List<Person> getAllPersons() {
        try {
            personDAO = PersonDaoFactory.create();
            return Arrays.asList(personDAO.getAll());
        } catch (PersonDaoException ex) {
            throw new BusinessException("There is a problem to obtain the array of persons from the database", ex);
        }
    }

    @Override
    public Person getPersonById(Integer id) {
        try {
            return personDAO.getByPrimaryKey(id);
        } catch (PersonDaoException ex) {
            throw new BusinessException("There is a problem to get the person id: " + id, ex);
        }
    }

    @Override
    public boolean deletePersons(List<Integer> codes) {
        try {
            personDAO = PersonDaoFactory.create();
            for (Integer code : codes) {
                personDAO.delete(new PersonPK(code));
            }
            return true;
        } catch (PersonDaoException e) {
            throw new BusinessException("Could not delete any record from the database", e);
        }
    }

    @Override
    public boolean savePerson(Person person) {
//        System.out.println(personDAO.getUserConnection());
//        Connection conn = personDAO.getUserConnection();
        try {
            personDAO = PersonDaoFactory.create();
//            conn = Gatekeeper.open();
//            conn.setAutoCommit(false);
//            personDAO.setUserConnection(conn);

            if (person.getIdPerson() == null) {
                personDAO.insert(person);
            } else {
                personDAO.update(person.createPK(), person);
            }

//            conn.commit();
            return true;

        } catch (PersonDaoException e) {
            e.printStackTrace();
            throw new BusinessException("It cannot save the person to the database", e);
        }
    }
}
