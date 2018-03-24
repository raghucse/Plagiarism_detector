package edu.neu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.neu.user.ApplicationUser;
import edu.neu.user.Role;
import edu.neu.user.UserRequest;

public class UserRequestTests {
	
	@Test
	public void testUsername() {
		UserRequest user = new UserRequest();
		String username = "abc";
		user.setUsername(username);
		assertEquals(username, user.getUsername());
	}
	
	@Test
	public void testPassword() {
		UserRequest user = new UserRequest();
		String password = "abc@123";
		user.setPassword(password);
		assertEquals(password, user.getPassword());
	}
	
	@Test
	public void testRoles() {
		UserRequest user = new UserRequest();
		Role role = Role.GRADER;
		user.setRole(role);
		assertEquals(role, user.getRole());
		role = Role.PROFESSOR;
		user.setRole(role);
		assertEquals(role, user.getRole());
	}
	
}
