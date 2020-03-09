package com.dinamohs.webpage.services.couriers;

import com.dinamohs.webpage.services.rules.UserRules;
import com.dinamohs.webpage.system.dto.User;

/**
 * Using Singleton Pattern for services of one {@link User#getClass() label}
 * @author crealhex
 */
public class UserTrack implements UserRules {

    private static UserTrack userTrack;
    // UserDAO userDAO;

    private UserTrack() {
        userTrack = new UserTrack();
    }

    public static UserTrack getInstance() {
        return userTrack;
    }

    @Override
    public boolean verifyUserData() {
        try {
//            userTrack = UserDaoFactory();
            final String SQL_WHERE = "username = ? AND password = ?";
        } catch (Exception ex) {

        }
        return false;
    }
}
