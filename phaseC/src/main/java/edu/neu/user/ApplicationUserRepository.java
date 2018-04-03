package edu.neu.user;

import org.springframework.data.jpa.repository.JpaRepository;

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
}