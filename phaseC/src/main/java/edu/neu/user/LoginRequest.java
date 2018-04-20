package edu.neu.user;

public class LoginRequest {
    /**
     * @return Returns the users' username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Assigns a username for the user
     * @param username is the username to be assigned for the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    String username;

    /**
     * @return Returns the users' password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user
     * @param password is the string value which is to be used
     *                 as the users' password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    String password;

}
