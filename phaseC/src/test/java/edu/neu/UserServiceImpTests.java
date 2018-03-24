package edu.neu;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.neu.user.ApplicationUser;
import edu.neu.user.ApplicationUserRepository;
import edu.neu.user.UserServiceImpl;

public class UserServiceImpTests {

	@Mock
	private ApplicationUserRepository userRepository;
	
	@Mock
	private UserServiceImpl serviceImpl;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testPresentUser() {
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUsername("PresentUser");
		applicationUser.setPassword("pwd");
		serviceImpl.save(applicationUser);
		when(userRepository.findByUsername("PresentUser")).thenReturn(applicationUser);
		assertEquals(applicationUser, userRepository.findByUsername("PresentUser"));
	}
	
}
