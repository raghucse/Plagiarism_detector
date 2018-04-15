package edu.neu.user;

/**
 * Remove request from the client
 */
public class RemoveRequest {

    /**
     * username that received through the request
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * username received from the request
     * @param userName user name to be removed
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    String userName;
}
