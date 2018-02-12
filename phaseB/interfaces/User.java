package plagiarismdetector;

/**
 * Interface of user to log in and off to the system
 * An user is an actor in the system. Users need to be registered in the system
 * @author Team 106
 * @version 1.0
 */
public interface User {

	/**
	 * Does things to be done when an user logs in
	 * Primarily initializes cache and other data structures for the user.
	 */
    public void onLogin();
    public void onLogout();
    public void addUserLog(String log);
    public String showUserLogs();

}
