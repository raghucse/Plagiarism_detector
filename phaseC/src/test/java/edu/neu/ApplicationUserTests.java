package edu.neu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.neu.user.ApplicationUser;
import edu.neu.user.Role;

public class ApplicationUserTests {

	@Test
	public void testId() {
		ApplicationUser user = new ApplicationUser();
		int id = 5;
		user.setId(id);
		assertEquals(id, user.getId());
	}
	
	@Test
	public void testUsername() {
		ApplicationUser user = new ApplicationUser();
		String username = "abc";
		user.setUsername(username);
		assertEquals(username, user.getUsername());
	}
	
	@Test
	public void testPassword() {
		ApplicationUser user = new ApplicationUser();
		String password = "abc@123";
		user.setPassword(password);
		assertEquals(password, user.getPassword());
	}
	
	@Test
	public void testRoles() {
		ApplicationUser user = new ApplicationUser();
		Role role = Role.GRADER;
		user.setRole(role);
		assertEquals(role, user.getRole());
		role = Role.PROFESSOR;
		user.setRole(role);
		assertEquals(role, user.getRole());
	}
	
}
