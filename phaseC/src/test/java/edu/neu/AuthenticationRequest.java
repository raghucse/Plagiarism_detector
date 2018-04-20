package edu.neu;

public class AuthenticationRequest {
    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    String username;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    String password;
}
