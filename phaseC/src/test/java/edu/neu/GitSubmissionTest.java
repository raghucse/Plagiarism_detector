package edu.neu;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.neu.models.GitSubmission;

public class GitSubmissionTest {
	
	private String DUMMY_STUDENT_NAME = "mockuser";

	@Test
	public void test01() {
		GitSubmission submission = new GitSubmission(DUMMY_STUDENT_NAME, "https://github.com/bharat94/hello_world.git");
		List<String> fileNames = new ArrayList<>();
		for (File f : submission.getFiles()) {
			fileNames.add(f.getName());
		}
		
		assertEquals(1, fileNames.size());
		assertEquals("mock.py", fileNames.get(0));
	}
	
	
	@Test
	public void testFilesInsideFolders() {
		GitSubmission submission = new GitSubmission(DUMMY_STUDENT_NAME, "https://github.com/qujunhao1549/testRepo.git");
		assertEquals(6, submission.getFiles().size());
	}
	
}
