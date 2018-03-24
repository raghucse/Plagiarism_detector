package edu.neu;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.neu.user.ApplicationUser;
import edu.neu.user.ApplicationUserRepository;
import edu.neu.user.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpTests {

	@Mock
	private ApplicationUserRepository applicationUserRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUsername("PresentUser");
		applicationUser.setPassword("pwd");
		when(applicationUserRepository.findByUsername("PresentUser")).thenReturn(applicationUser);
	}
	
	@Test
	public void testPresentUser() {
		UserDetailsServiceImpl serviceImpl = new UserDetailsServiceImpl(applicationUserRepository);
		UserDetails user = serviceImpl.loadUserByUsername("PresentUser");
		assertEquals("PresentUser", user.getUsername());
		assertEquals("pwd", user.getPassword());
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void testAbsentUser() {
		UserDetailsServiceImpl serviceImpl = new UserDetailsServiceImpl(applicationUserRepository);
		serviceImpl.loadUserByUsername("AbsentUser");
	}
	
}
