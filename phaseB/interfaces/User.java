package plagiarismdetector;

/**
 * Interface of user to log in and off to the system.
 *
 * @author Team 106
 * @version 1.0
 */
public interface User {

    public boolean login(String email, String password);
    public boolean logout();

}
