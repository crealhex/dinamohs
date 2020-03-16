package com.dinamohs.webpage.system.jdbc.mysql;

import com.dinamohs.webpage.services.couriers.Gatekeeper;
import com.dinamohs.webpage.system.dao.PersonDAO;
import com.dinamohs.webpage.system.dto.Person;
import com.dinamohs.webpage.system.dto.PersonPK;
import com.dinamohs.webpage.system.exceptions.PersonDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PersonMySQL implements PersonDAO {

    private Connection conn;
    private PreparedStatement statement;
    private ResultSet result;

    private final String TABLE_NAME = "person";
    private final String SQL_SELECT = "SELECT id_person, first_name, last_name FROM " + TABLE_NAME;
    private final String SQL_INSERT = "INSERT INTO " + TABLE_NAME + " (id_person, first_name, last_name) VALUES (?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET id_person = ?, first_name = ?, last_name = ? WHERE id_person = ?";
    private final String SQL_DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id_person = ?";

    @Override
    public PersonPK insert(Person person) throws PersonDaoException {
        long initialTime = System.currentTimeMillis();
        try {
            conn = Gatekeeper.open();
            statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            int index = 0;

            if (person.getIdPerson() != null) {
                statement.setInt(++index, person.getIdPerson());
            } else {
                statement.setNull(++index, Types.INTEGER);
            }

            statement.setString(++index, person.getFirstName());
            statement.setString(++index, person.getLastName());
//            System.out.println("Executing... [" + SQL_INSERT + " with Person: " + person);
            int rows = statement.executeUpdate();
            long finalTime = System.currentTimeMillis();
//            System.out.println(rows + " rows affected (" + (finalTime - initialTime) + " ms)");

            result = statement.getGeneratedKeys();
            if (result != null && result.next()) {
                person.setIdPerson(result.getInt(1));
            }
            return person.createPK();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersonDaoException("Exception: " + e.getMessage(), e);

        } finally {
            Gatekeeper.close(conn, statement, result);
        }
    }

    @Override
    public void update(PersonPK pk, Person person) throws PersonDaoException {
        long initialTime = System.currentTimeMillis();
        try {
            conn = Gatekeeper.open();
            statement = conn.prepareStatement(SQL_UPDATE);
            int index = 0;

            if (person.getIdPerson() != null) {
                statement.setInt(++index, person.getIdPerson());
            } else {
                statement.setNull(++index, Types.INTEGER);
            }

            statement.setString(++index, person.getFirstName());
            statement.setString(++index, person.getLastName());

            if (pk.getId() != null) {
                statement.setInt(++index, pk.getId());
            } else {
                statement.setNull(++index, Types.INTEGER);
            }

//            System.out.println("Executing... [" + SQL_UPDATE + " with Person: " + person);
            int rows = statement.executeUpdate();
            long finalTime = System.currentTimeMillis();
//            System.out.println(rows + " rows affected (" + (finalTime - initialTime) + " ms)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersonDaoException("Exception: " + e.getMessage(), e);

        } finally {
            Gatekeeper.close(conn, statement, result);
        }
    }

    @Override
    public void delete(PersonPK pk) throws PersonDaoException {
        try {
            conn = Gatekeeper.open();
            statement = conn.prepareStatement(SQL_DELETE);

            if (pk.getId() != null) {
                statement.setInt(1, pk.getId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersonDaoException("Exception: " + e.getMessage(), e);

        } finally {
            Gatekeeper.close(conn, statement, result);
        }
    }

    @Override
    public Person getByPrimaryKey(PersonPK personPK) throws PersonDaoException {
        return getByPrimaryKey(personPK.getId());
    }

    @Override
    public Person getByPrimaryKey(Integer id) throws PersonDaoException {
        Person[] personArray  = getByDynamicSelect(SQL_SELECT + " WHERE id_person = ?", new Object[]{id});
        return personArray.length == 0 ? null : personArray[0];
    }

    @Override
    public Person[] getAll() throws PersonDaoException {
        return getByDynamicSelect(SQL_SELECT + " ORDER BY id_person", null);
    }

    @Override
    public Person[] getByDynamicSelect(String SCRIPT, Object[] params) throws PersonDaoException {
        try {
            conn = Gatekeeper.open();
//            System.out.println("Executing... [" + SCRIPT + "]");
            statement = conn.prepareStatement(SCRIPT);

            for (int i = 0; params != null && i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            result = statement.executeQuery();
            return fetchMultiResult();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersonDaoException("Exception: " + e.getMessage(), e);
        } finally {
            Gatekeeper.close(conn, statement, result);
        }
    }

    @Override
    public Connection getUserConnection() {
        return conn;
    }

    @Override
    public void setUserConnection(Connection conn) {
        this.conn = conn;
    }

    private Person[] fetchMultiResult() throws SQLException {
        Collection<Person> list = new ArrayList<>();
        while (result.next()) {
            Person person = new Person();
            fillPerson(person);
            list.add(person);
        }
        Person[] persons = new Person[list.size()];
        list.toArray(persons);
        return persons;
    }

    private void fillPerson(Person person) throws SQLException {
        person.setIdPerson(result.getInt("id_person"));
        person.setFirstName(result.getString("first_name"));
        person.setLastName(result.getString("last_name"));
    }
}
