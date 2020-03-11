package com.dinamohs.webpage.services.couriers;

import com.dinamohs.webpage.services.rules.UserRules;
import com.dinamohs.webpage.system.dao.UserDAO;
import com.dinamohs.webpage.system.dto.User;
import com.dinamohs.webpage.system.factory.UserDaoFactory;

/**
 * Using Singleton Pattern for services of one User
 * @author crealhex
 */
public class UserTrack implements UserRules {

    private static UserTrack userTrack;
    UserDAO userDAO;

    private UserTrack() {
//        userTrack = new UserTrack();
    }

    public static UserTrack getInstance() {
        if (userTrack == null) {
            userTrack = new UserTrack();
        }
        return userTrack;
    }

    @Override
    public boolean verifyUserData(User user) {
        try {
            userDAO = UserDaoFactory.create(); // TODO
            final String SQL_WHERE = "username = ? AND password = ?";
            Object[] params = {
                    user.getUsername(),
                    user.getPassword(),
            };

            User[] users = userDAO.getByWhere(SQL_WHERE, params);

            if (users.length > 0) {
                return true;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            // TODO throw-> BusinessException
        }
        return false;
    }
}
