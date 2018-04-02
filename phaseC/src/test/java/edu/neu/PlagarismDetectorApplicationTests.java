package edu.neu;


import static java.util.Collections.emptyList;
import static java.util.Collections.sort;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import edu.neu.user.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class PlagarismDetectorApplicationTests extends AbstractMvc{

	@Test
	public void testRegisterAndLogin() throws Exception {

		mockMvc.perform(post("/registration")
				.param("username","raghu@neu.com")
				.param("password", "Test@1234")
				.param("role", "PROFESSOR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.msg", is("registration successful")));

		login("raghu@neu.com", "Test@1234").andExpect(status().isOk());

	}

}
