package com.dinamohs.webpage.system.dao;

import com.dinamohs.webpage.system.dto.User;
import com.dinamohs.webpage.system.dto.UserPK;
import com.dinamohs.webpage.system.exceptions.UserDaoException;

public interface UserDAO {

    /**
     * Inserts a new row in the user table.
     */
    UserPK insert(User user) throws UserDaoException;

    /**
     * Updates a single row in the user table.
     */
    void update(UserPK pk, User user) throws UserDaoException;

    /**
     * Deletes a single row in the user table.
     */
    void delete(UserPK pk) throws UserDaoException;

    /**
     * Returns the row of the user table that matches the specified primary_key value.
     */
    User getByPK(UserPK pk) throws UserDaoException;

    /**
     * Returns the row from the user table that match the criteria 'id_user:idUser'.
     */
    User getByPK(Integer id) throws UserDaoException;

    /**
     * Returns all rows from the user table.
     */
    User[] getAll() throws UserDaoException;

    /**
     * Returns all rows from the user table that match the criteria 'id_person = :idPerson'.
     */
    User[] getByPerson(Integer id) throws UserDaoException;

    /**
     * Sets the value of max rows.
     */
    void setMaxRows(int rows);

    /**
     * Gets the value of max rows.
     */
    void getMaxRows();

    /**
     * Returns all rows from the user table that match the specified arbitrary SQL statement.
     */
    User[] getBySelect() throws UserDaoException;

    /**
     * Returns all rows from the user table that match the specified arbitrary SQL statement.
     */
    User[] getByWhere(final String SQL) throws UserDaoException;
}
