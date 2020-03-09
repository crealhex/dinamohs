package com.dinamohs.webpage.system.dao;

import com.dinamohs.webpage.system.dto.User;
import com.dinamohs.webpage.system.dto.UserPK;
import com.dinamohs.webpage.system.exceptions.UserDaoException;

public interface UserDAO {

    /**
     * Inserts a new row in the user table
     */
    UserPK insert(User user) throws UserDaoException;

    /**
     * Updates a single row in the user table
     */
    void update(UserPK pk, User user) throws UserDaoException;

    /**
     * Deletes a single row in the user table
     */
    void delete(UserPK pk) throws UserDaoException;

}
