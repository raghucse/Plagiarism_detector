package edu.neu.user;

/**
 * This interface deals with the user services
 */
public interface UserService {
    /**
     * Saves the user in the list of registered users
     * @param user is the user to be added to the user list
     */
    void save(ApplicationUser user);

    /**
     * Searches for a particular user
     * @param username is the username of the user to be searched
     * @return returns the user found against the specified username
     */
    public ApplicationUser findByUsername(String username);

    /**
     * remove user from the db
     * @param user useranme of user to be removed
     * @return 1 if successful else 0
     */
    public int removeUser(String user);

    /**
     *
     * @return count of users in the database
     */
    public long totalUserCount();
}