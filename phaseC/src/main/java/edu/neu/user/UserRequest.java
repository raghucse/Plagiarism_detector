package edu.neu.user;

/**
 * This class handles the users' requests
 */
public class UserRequest {
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

    /**
     * @return Returns the role of the user
     */
    public Role getRole() {
        return role;
    }


    /**
     * Sets the role for the user
     * @param role is the Role to be assigned to the user
     */
    public void setRole(Role role) {
        this.role = role;
    }

    String username;
    String password;
    Role role;
}
