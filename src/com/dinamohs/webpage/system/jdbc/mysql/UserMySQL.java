package com.dinamohs.webpage.system.jdbc.mysql;

import com.dinamohs.webpage.system.dao.DAO;
import com.dinamohs.webpage.system.dao.UserDAO;
import com.dinamohs.webpage.system.dto.User;
import com.dinamohs.webpage.system.dto.UserPK;
import com.dinamohs.webpage.system.exceptions.UserDaoException;

import java.sql.Connection;

public class UserMySQL extends DAO implements UserDAO {

    protected Connection conn;

    @Override
    public UserPK insert(User user) throws UserDaoException {
        return null;
    }

    @Override
    public void update(UserPK pk, User user) throws UserDaoException {

    }

    @Override
    public void delete(UserPK pk) throws UserDaoException {

    }

    @Override
    public User getByPK(UserPK pk) throws UserDaoException {
        return null;
    }

    @Override
    public User getByPK(Integer id) throws UserDaoException {
        return null;
    }

    @Override
    public User[] getAll() throws UserDaoException {
        return new User[0];
    }

    @Override
    public User[] getByPerson(Integer id) throws UserDaoException {
        return new User[0];
    }

    @Override
    public void setMaxRows(int rows) {

    }

    @Override
    public void getMaxRows() {

    }

    @Override
    public User[] getBySelect() throws UserDaoException {
        return new User[0];
    }

    @Override
    public User[] getByWhere(String SQL) throws UserDaoException {
        return new User[0];
    }
}
