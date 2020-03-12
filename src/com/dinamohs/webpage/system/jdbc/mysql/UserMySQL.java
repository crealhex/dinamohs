package com.dinamohs.webpage.system.jdbc.mysql;

import com.dinamohs.webpage.services.couriers.Gatekeeper;
import com.dinamohs.webpage.system.dao.DAO;
import com.dinamohs.webpage.system.dao.UserDAO;
import com.dinamohs.webpage.system.dto.User;
import com.dinamohs.webpage.system.dto.UserPK;
import com.dinamohs.webpage.system.exceptions.UserDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class UserMySQL extends DAO implements UserDAO {

    protected Connection conn;

    protected final String TABLE_NAME = "user";
    protected final String SQL_SELECT = "SELECT id_user, id_person, username, password FROM " + TABLE_NAME;

    protected int maxRows;

    /**
     * (non-Javadoc)
     * @see com.dinamohs.webpage.system.dao.UserDAO#insert(User)
     */
    @Override
    public UserPK insert(User user) throws UserDaoException {
        return null;
    }

    /**
     * @see com.dinamohs.webpage.system.dao.UserDAO#update(UserPK, User)
     */
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
        this.maxRows = rows;
    }

    @Override
    public int getMaxRows() {
        return this.maxRows;
    }

    @Override
    public User[] getBySelect(String SCRIPT, Object[] params) throws UserDaoException {
        return new User[0];
    }

    /**
     * Returns all rows from the user table that match the specified arbitrary SQL statement.
     * @param SCRIPT SQL
     * @return array of User
     * @throws UserDaoException for DAO pattern
     */
    @Override
    public User[] getByWhere(String SCRIPT, Object[] params) throws UserDaoException {

        final boolean checkConn = (this.conn != null);
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            conn = checkConn ? this.conn : Gatekeeper.open();
            final String ENTIRE_SCRIPT = SQL_SELECT + " WHERE " + SCRIPT;
            System.out.println("Executing... [" + ENTIRE_SCRIPT + "]");

            statement = conn.prepareStatement(ENTIRE_SCRIPT);
            statement.setMaxRows(maxRows);

            for (int i = 0; params != null && i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            result = statement.executeQuery();
            return fetchMultiResults(result);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDaoException("Exception " + e.getMessage(), e);
        } finally {
            // TODO Gatekeeper.close();
            Gatekeeper.close(conn, statement, result);
        }
    }

    private User[] fetchMultiResults(ResultSet result) throws SQLException { // TODO
        Collection<User> list = new ArrayList<>();
        while (result.next()) {
            User user = new User();
            fillUser(user, result);
            list.add(user);
        }
        User[] users = new User[list.size()];
        list.toArray(users);
        return users;
    }

    private void fillUser(User user, ResultSet result) throws SQLException {
        user.setIdUser(result.getInt("id_user"));
        user.setIdPerson(result.getInt("id_person"));
        user.setUsername(result.getString("username"));
        user.setPassword(result.getString("password"));
    }
}
