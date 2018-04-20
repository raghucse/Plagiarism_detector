package edu.neu;


import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.reports.Report;
import edu.neu.statistics.StatsRes;
import edu.neu.user.Role;
import edu.neu.user.UserInfoRes;
import edu.neu.user.UserResponse;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;


public class PlagarismDetectorApplicationTests extends AbstractMvc{

	@Test
	public void testRegisterAndLogin() throws Exception {

		mockMvc.perform(post("/registration")
				.param("username","raghu@neu.com")
				.param("password", "Test@1234")
				.param("role", "PROFESSOR"))
				.andExpect(status().isOk());

		login("raghu@neu.com", "Test@1234").andExpect(status().isOk());

	}

	@Test
	public void testRunPlagiarism() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
        register("admin@example.com","123456","ADMIN");
		ResultActions logResult = login("admin@example.com", "123456");
		String token = extractToken(logResult);
		String userName = extractUserName(logResult);
		UserInfoRes userInfoRes = getUserInfo(userName, token);



		ResultActions result = mockMvc.perform(
				post("/plagiarism/run")
						.header("Authorization", token)
						.param("description","Sample description")
						.param("gitUrls", "https://github.com/bharat94/testRepo1.git, https://github.com/bharat94/testRepo2.git")
						.param("sharedUsers", "")
						.param("runName", "testRun"));
		Thread.sleep(10000);
	//	assertEquals("Plagiarism run started",result.andReturn().getResponse().getContentAsString());
		//get reports by id
		String res = "[{\"id\":1,\"name\":\"Sample description\",\"owner\":1,\"reportScore\":0.1612665072227597,\"reportFile\":{\"reportMessage\":\"Report Content\",\"comparisonList\":[{\"filename1\":\"student1-file1.py\",\"filename2\":\"student2-file2.py\",\"scores\":{\"totalScore\":0.1612665072227597,\"subScores\":\"LCS:0.2087912087912088; LVDistance:0.20879120879120883; CosineSimilarity:0.06621710408586144; \"}},{\"filename1\":\"student1-file1.py\",\"filename2\":\"student2-file1.py\",\"scores\":{\"totalScore\":0.1695617381920277,\"subScores\":\"LCS:0.2087912087912088; LVDistance:0.20879120879120883; CosineSimilarity:0.09110279699366539; \"}},{\"filename1\":\"student1-file2.py\",\"filename2\":\"student2-file2.py\",\"scores\":{\"totalScore\":1.0,\"subScores\":\"LCS:1.0; LVDistance:1.0; CosineSimilarity:1.0; \"}},{\"filename1\":\"student1-file2.py\",\"filename2\":\"student2-file1.py\",\"scores\":{\"totalScore\":0.8168489401184441,\"subScores\":\"LCS:0.9615384615384616; LVDistance:0.9615384615384616; CosineSimilarity:0.5274698972784089; \"}}]}}]";

		ResultActions resultReport = mockMvc.perform(get("/report/user/reportIds/{userId}", userInfoRes.getUid())
						.header("Authorization", token));
		resultReport.andExpect(status().isOk());

		String test = resultReport.andReturn().getResponse().getContentAsString();

		resultReport = mockMvc.perform(
				get("/report/userId/{userId}",userInfoRes.getUid())
						.header("Authorization", token));
		resultReport.andExpect(status().isOk());
	}

	@Test
	public void testGetStats() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		register("sa2@example.com","123456","PROFESSOR");
		ResultActions logResult = login("sa2@example.com", "123456");
		String token = extractToken(logResult);


		ResultActions result = mockMvc.perform(
				get("/stats")
						.header("Authorization", token));
		result.andExpect(status().isOk());
		StatsRes statsRes = mapper.readValue(result.andReturn().getResponse().getContentAsString(), StatsRes.class);


		String nameOS = "os.name";
		String versionOS = "os.version";
		String architectureOS = "os.arch";


		assertEquals(statsRes.getOsDetails().getOsArch(), System.getProperty(architectureOS));
		assertEquals(statsRes.getOsDetails().getOsName(), System.getProperty(nameOS));
		assertEquals(statsRes.getOsDetails().getOsVersion(), System.getProperty(versionOS));

	}

	@Test
	public void testAdminAddAndRemoveUsingAdmin() throws Exception {
		ResultActions logResult = login("admin@example.com", "123456");
		String token = extractToken(logResult);

		mockMvc.perform(post("/add/admin")
				.param("username","admin5@example.com")
				.param("password", "123456")
				.param("role","ADMIN")
				.header("Authorization", token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.msg", is("Admin registration successful")));


		mockMvc.perform(post("/remove/user")
				.param("userName","admin5@example.com")
				.header("Authorization", token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.msg", is("user removed successfully")));
	}

	@Test
	public void testSearchUser() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ResultActions logResult = login("admin@example.com", "123456");
		String token = extractToken(logResult);

		register("adminSearch@example.com","123456","PROFESSOR");

		ResultActions result = mockMvc.perform(get("/user/info")
				.param("userName","adminSearch@example.com")
				.header("Authorization", token))
				.andExpect(status().isOk());

		UserResponse userResponse = mapper.readValue(result.andReturn().getResponse().getContentAsString(), UserResponse.class);
		assertEquals(userResponse.getUserName(), "adminSearch@example.com");
		assertEquals(userResponse.getRole(), Role.PROFESSOR);

	}
}
