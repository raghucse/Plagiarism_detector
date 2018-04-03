package edu.neu.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class determines the Admin functionalities
 */
public class Admin implements SystemUser{

	/**
	 * This function specifies the actions admin can perform
	 * once he has successfully logged in
	 */
	public void onLogin() {
		// log user login here
	}

	/**
	 * This function specifies the actions admin can perform
	 * once he has successfully logged out
	 */
	public void onLogout() {
		// Log user logouts here
	}

	/**
	 * Allows the admin to maintain the user log
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
