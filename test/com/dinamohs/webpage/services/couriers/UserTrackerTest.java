package com.dinamohs.webpage.services.couriers;

import com.dinamohs.webpage.system.dto.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTrackerTest {

    @Test
    public void verifyUserData_test() {
        UserTracker track = UserTracker.getInstance();
        User user = new User();

        user.setUsername("crealhex");
        user.setPassword("3230184");
        boolean condition = track.verifyUserData(user);

        assertTrue("The user is already registered", condition);
    }

    @Test
    public void verifyUserData_wrong_test() {
        UserTracker track = UserTracker.getInstance();
        User user = new User();

        user.setUsername("crealhex");
        user.setPassword("14335353");
        boolean condition = track.verifyUserData(user);

        assertFalse("The user doesn't exist", condition);
    }
}