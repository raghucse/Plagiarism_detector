package edu.neu;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import edu.neu.reports.PlagiarismRunRequest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;


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

	@Test
	public void testRunPlagiarism() throws Exception {
		List<String> gitUrls = new ArrayList<>();

		gitUrls.add("https://github.com/bharat94/testRepo1.git");
		gitUrls.add("https://github.com/bharat94/testRepo2.git");

		PlagiarismRunRequest plagRun = new PlagiarismRunRequest();
		plagRun.setDescription("Sample run");
		plagRun.setGitUrls(gitUrls);
        plagRun.setSharedUsers(new ArrayList<>());
		ResultActions result = mockMvc.perform(
				post("/plagiarism/run")
						.header("Authorization", getToken())
						.param("description",json(plagRun))
						.param("gitUrls", json(gitUrls))
						.param("sharedUsers", json(new ArrayList<>()))
						.contentType(MediaType.APPLICATION_JSON));
		assertEquals("Plagiarism run started",result.andReturn().getResponse().getContentAsString());
	}

}
