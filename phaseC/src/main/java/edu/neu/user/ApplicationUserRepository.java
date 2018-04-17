package edu.neu.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This interface maintains the information of all the registered users
 */
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    /**
     * Searches for a particular user by the username
     * @param username is String value which is to be used to search
     *                 for a particular user
     * @return returns the user found against the specified username
     */
    ApplicationUser findByUsername(String username);

    /**
     *
     * @param username username of the user to be removed
     * @return 0 if unsuccessful and 1 if successful
     */
    @Transactional
    Integer deleteByUsername(String username);

}