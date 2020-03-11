package com.dinamohs.webpage.services.rules;

import com.dinamohs.webpage.system.dto.User;

/**
 * @author crealhex
 */
public interface UserRules {

    /**
     * Check if a user exists in the database
     * @return boolean
     */
    boolean verifyUserData(User user);

}
