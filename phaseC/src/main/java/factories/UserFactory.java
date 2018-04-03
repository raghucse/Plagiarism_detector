package factories;

import edu.neu.models.SystemUser;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the addition and deletion of users
 */
public class UserFactory {

	/**
	 * Creates a new user
	 * @return a newly created user
	 */
	public SystemUser createUser() {
		return null;
	}

	/**
	 * Removes a particular user from the user repository
	 * @param userId is the id of the user to be removed
	 * @return returns true if the user is removed, else returns false
	 */
	public boolean deleteUser(String userId) {
		return false;
	}

	/**
	 * Returns a particular user
	 * @param userId is the id of the user to be returned
	 * @return the user found against the specified id
	 */
	public SystemUser getUser(String userId) {
		return null;
	}

	/**
	 * @return Returns list of users
	 */
	public List<SystemUser> getUsers() {
		return new ArrayList<>();
	}
}
