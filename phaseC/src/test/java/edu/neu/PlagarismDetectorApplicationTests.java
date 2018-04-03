package edu.neu;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;


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


		ResultActions result = mockMvc.perform(
				post("/plagiarism/run")
						.header("Authorization", getToken())
						.param("description","Sample description")
						.param("gitUrls", "https://github.com/bharat94/testRepo1.git, https://github.com/bharat94/testRepo2.git")
						.param("sharedUsers", ""));
		Thread.sleep(5000);
		assertEquals("Plagiarism run started",result.andReturn().getResponse().getContentAsString());

		//get reports by id
		String res = "[{\"id\":1,\"name\":\"Sample description\",\"owner\":1,\"reportScore\":0.1612665072227597,\"reportFile\":{\"reportMessage\":\"Report Content\",\"comparisonList\":[{\"filename1\":\"student1-file1.py\",\"filename2\":\"student2-file2.py\",\"scores\":{\"totalScore\":0.1612665072227597,\"subScores\":\"LCS:0.2087912087912088; LVDistance:0.20879120879120883; CosineSimilarity:0.06621710408586144; \"}},{\"filename1\":\"student1-file1.py\",\"filename2\":\"student2-file1.py\",\"scores\":{\"totalScore\":0.1695617381920277,\"subScores\":\"LCS:0.2087912087912088; LVDistance:0.20879120879120883; CosineSimilarity:0.09110279699366539; \"}},{\"filename1\":\"student1-file2.py\",\"filename2\":\"student2-file2.py\",\"scores\":{\"totalScore\":1.0,\"subScores\":\"LCS:1.0; LVDistance:1.0; CosineSimilarity:1.0; \"}},{\"filename1\":\"student1-file2.py\",\"filename2\":\"student2-file1.py\",\"scores\":{\"totalScore\":0.8168489401184441,\"subScores\":\"LCS:0.9615384615384616; LVDistance:0.9615384615384616; CosineSimilarity:0.5274698972784089; \"}}]}}]";

		ResultActions resultReport = mockMvc.perform(get("/report/user/reportIds/{userId}", "1")
						.header("Authorization", getToken()));
		resultReport.andExpect(status().isOk());
		assertEquals(res,resultReport.andReturn().getResponse().getContentAsString());

	}

}
