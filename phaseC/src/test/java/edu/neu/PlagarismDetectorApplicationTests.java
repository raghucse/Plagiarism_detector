package edu.neu;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.user.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

	@Test
	public void testRegister() throws Exception {
		User req = new User();
		req.setUsername("raghucse");
		req.setPassword("Test@1234");
		req.setRole(Role.PROFESSOR);
		String stringifiedRequest = objectMapper.writeValueAsString(req);

		mvc.perform(post("/registration")
				.param("username","raghucse")
				.param("password", "Test@1234"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.msg", is("registration successful")));
	}

	@Test
	public void testLogin() throws Exception {

		User user = new User();
		user.setUsername("raghucse");
		user.setPassword("Test@1234");
		user.setRole(Role.PROFESSOR);

		when(userRepository.findByUsername("raghucse")).thenReturn(user);
		mvc.perform(post("/login")
				.param("username", "raghucse")
				.param("password","Test@1234")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.msg", is("login successful")));
	}
}
