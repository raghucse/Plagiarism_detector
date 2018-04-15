package edu.neu.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * This class implements the functionalities of the UserService interface
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private ApplicationUserRepository userRepository;

    /**
     * Saves the user in the list of registered users
     * @param user is the user to be added to the user list
     */
    public void save(ApplicationUser user) {
        userRepository.save(user);
    }

    /**
     * Searches for a particular user
     * @param username is the username of the user to be searched
     * @return returns the user found against the specified username
     */
    public ApplicationUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     *
     * @return count of users in the database
     */
    public long totalUserCount(){
        return userRepository.count();
    }

    /**
     * remove user from the db
     * @param user useranme of user to be removed
     * @return 1 if successful else 0
     */
    public int removeUser(String user){
        return userRepository.deleteByUsername(user);
    }
}