package edu.neu.models;

import java.util.List;

/**
 * Interface of user to log in and off to the system
 * An user is an actor in the system. Users need to be registered in the system
 * @author Team 106
 * @version 1.0
 */
public interface SystemUser {

	/**
	 * Does things to be done when an user logs in
	 * Primarily initializes cache and other data structures for the user.
	 */
    public void onLogin();
    
    /**
	 * Does things to be done when an user logs out
	 * Primarily clears cache and destroys other data structures for the user.
	 */
    public void onLogout();
    
    /**
     * Adds a line of log to the users log
     * A user log contains important information about the user activity
     * @param log
     */
    public void addUserLog(String log);
    
    /**
     * Return the user logs recorded for an user
     * @return the logs for an user
     */
    public List<String> showUserLogs();
	
}
