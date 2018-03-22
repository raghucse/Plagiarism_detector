package factories;

import java.util.ArrayList;
import java.util.List;

import edu.neu.models.SystemUser;

public class UserFactory {

	public SystemUser createUser() {
		return null;
	}
	
	public boolean deleteUser(String userId) {
		return false;
	}
	
	public SystemUser getUser(String userId) {
		return null;
	}
	
	public List<SystemUser> getUsers() {
		return new ArrayList<>();
	}
}
