package edu.neu;


import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.user.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class PlagarismDetectorApplicationTests{

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private UserValidator validator;

	@Test
	public void testRegister() throws Exception {
		User req = new User();
		req.setUsername("raghucse");
		req.setPassword("Test@1234");
		req.setRole(Role.PROFESSOR);
		String stringifiedRequest = objectMapper.writeValueAsString(req);

		mvc.perform(post("/registration")
				.content(stringifiedRequest)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.msg", is("registration successful")));
	}

	@Test
	public void testLogin() throws Exception {

		User user = new User();
		user.setUsername("raghucse");
		user.setPassword("Test@1234");
		user.setRole(Role.PROFESSOR);


		LoginRequest req = new LoginRequest();
		req.setUsername("raghucse");
		req.setPassword("Test@1234");
		String stringifiedRequest = objectMapper.writeValueAsString(req);


		Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
		//testRegister();
		mvc.perform(post("/login")
				.content(stringifiedRequest)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.msg", is("login successful")));
	}
}
