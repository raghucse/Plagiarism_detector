package edu.neu;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import edu.neu.user.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class PlagarismDetectorApplicationTests{

	private MockMvc mvc;

	@Mock
	private UserService userService;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Mock
	private ApplicationUserRepository userRepository;

	@InjectMocks
	private UserController userController;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders
				.standaloneSetup(userController)
				.build();
	}

	@Test
	public void testRegister() throws Exception {

		mvc.perform(post("/registration")
				.param("username","raghucse")
				.param("password", "Test@1234"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.msg", is("registration successful")));
	}

}
