package edu.neu.factories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import factories.UserFactory;

public class UserFactoryTest {
	
	UserFactory userFactory;
	
	@Before
	public void init() {
		userFactory = new UserFactory();
	}

	@Test
	public void testCreateUser() {
		assertNull(userFactory.createUser());
	}
	
	@Test
	public void testDeleteUser() {
		assertFalse(userFactory.deleteUser(""));
	}
	
	@Test
	public void testGetUser() {
		assertNull(userFactory.getUser(""));
	}
	
	@Test
	public void testGetUsers() {
		assertEquals(0, userFactory.getUsers().size());
	}
	
}
