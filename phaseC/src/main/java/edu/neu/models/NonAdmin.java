package edu.neu.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class determines the other users' functionalities
 */
public class NonAdmin implements SystemUser{

	/**
	 * This function specifies the actions user can perform
	 * once he has successfully logged in
	 */
	public void onLogin() {
		// log user login here
	}

	/**
	 * This function specifies the actions user can perform
	 * once he has successfully logged out
	 */
	public void onLogout() {
		// Log user logouts here
	}

	/**
	 * This function allows the maintainence the user log
	 * @param log is a string value which specifies the log
	 */
	public void addUserLog(String log) {
		// add to user logs
	}

	/**
	 * @return the user log information as a list
	 */
	public List<String> showUserLogs() {
		return new ArrayList<>();
	}

}
