package com.dinamohs.webpage.services.rules;

import com.dinamohs.webpage.system.dto.User;

/**
 * @author crealhex
 */
public interface UserRules {

    /**
     * Checks if a user exists in the database.
     * @param user Object values entered by form. Requires username and
     *             password attributes to be verified.
     * @return boolean if exists or not.
     */
    boolean verifyUserData(User user);

}
