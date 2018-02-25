package edu.neu.user;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
