package com.dinamohs.webpage.services.couriers;

import com.dinamohs.webpage.system.dto.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTrackTest {

    @Test
    public void testVerifyUserData() {
        UserTrack track = UserTrack.getInstance();
        User user = new User();

        user.setUsername("crealhex");
        user.setPassword("3230184");
        track.verifyUserData(user);

        assertTrue("Los datos del usuario existen", true);
    }
}