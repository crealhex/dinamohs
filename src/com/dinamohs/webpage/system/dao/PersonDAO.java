package com.dinamohs.webpage.system.dao;

import com.dinamohs.webpage.system.dto.Person;
import com.dinamohs.webpage.system.dto.PersonPK;
import com.dinamohs.webpage.system.exceptions.PersonDaoException;

import java.sql.Connection;

public interface PersonDAO {

    PersonPK insert(Person person) throws PersonDaoException;

    void update(PersonPK pk, Person person) throws PersonDaoException;

    void delete(PersonPK pk) throws PersonDaoException;

    Person getByPrimaryKey(PersonPK personPK) throws PersonDaoException;

    Person getByPrimaryKey(Integer id) throws PersonDaoException;

    Person[] getAll() throws PersonDaoException;

    Person[] getByDynamicSelect(final String SCRIPT, Object[] params) throws PersonDaoException;

    Connection getUserConnection();

    void setUserConnection(Connection conn);

}
