package edu.neu;


import edu.neu.user.LoginRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginRequestTest {
    @Test
    public void testUsername() {
        LoginRequest user = new LoginRequest();
        String username = "abc";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testPassword() {
        LoginRequest user = new LoginRequest();
        String password = "abc@123";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }
}
