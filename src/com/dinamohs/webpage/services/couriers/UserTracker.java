package com.dinamohs.webpage.services.couriers;

import com.dinamohs.webpage.services.exceptions.BusinessException;
import com.dinamohs.webpage.services.rules.UserRules;
import com.dinamohs.webpage.system.dao.UserDAO;
import com.dinamohs.webpage.system.dto.User;
import com.dinamohs.webpage.system.exceptions.UserDaoException;
import com.dinamohs.webpage.system.factory.UserDaoFactory;

/**
 * This tracker works as a container for user services to get at any
 * point in the life of the application.
 * @author Luis Enco (crealhex)
 */
public class UserTracker implements UserRules {

    /**
     * Using singleton pattern, there is only one instance of UserTrack
     * on memory.
     */
    private static UserTracker userTracker;

    /**
     * This attribute allows communication with the data source of its
     * class.
     */
    UserDAO userDAO;

    /**
     * Private empty constructor with no arguments to make sense of
     * singleton pattern.
     */
    private UserTracker() {}

    /**
     * Gets a unique instance of this Tracker.
     * In case that doesn't exist, it will be created and obtained.
     * @return UserTracker instance.
     */
    public static UserTracker getInstance() {
        if (userTracker == null) {
            userTracker = new UserTracker();
        }
        return userTracker;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verifyUserData(User user) {
        try {
            userDAO = UserDaoFactory.create();
            final String SQL_WHERE = "username = ? AND password = ?";
            Object[] params = {
                    user.getUsername(),
                    user.getPassword(),
            };

            User[] users = userDAO.getByWhere(SQL_WHERE, params);

            if (users.length > 0) {
                return true;
            }

        } catch (UserDaoException ex) {
//            ex.printStackTrace();
            throw new BusinessException("Exists a problem to get the user", ex); // TODO check if it throws an exception correctly
        }
        return false;
    }
}
