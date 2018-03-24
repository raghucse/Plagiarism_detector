package edu.neu.models;

import java.util.ArrayList;
import java.util.List;

public class NonAdmin implements SystemUser{

	@Override
	public void onLogin() {
		// log user login here
	}

	@Override
	public void onLogout() {
		// Log user logouts here
	}

	@Override
	public void addUserLog(String log) {
		// add to user logs
	}

	@Override
	public List<String> showUserLogs() {
		return new ArrayList<>();
	}

}
